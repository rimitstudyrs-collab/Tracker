package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.SyllabusData
import com.example.ui.TrackerViewModel
import com.example.ui.components.AppHeaderBar
import com.example.ui.screens.AdminModalDialog
import com.example.ui.screens.AuthScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.SubjectModuleScreen
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: TrackerViewModel = viewModel()
            val isDarkTheme by viewModel.isDarkTheme.collectAsState()
            val userLicense by viewModel.userLicense.collectAsState()
            val showAuthScreen by viewModel.showAuthScreen.collectAsState()
            val currentView by viewModel.currentView.collectAsState()
            val selectedTrack by viewModel.selectedTrack.collectAsState()
            val showAdminModal by viewModel.showAdminModal.collectAsState()
            val isAdminUnlocked by viewModel.isAdminUnlocked.collectAsState()
            val adminMessage by viewModel.adminMessage.collectAsState()
            val generatedAdminCode by viewModel.generatedAdminCode.collectAsState()
            val authError by viewModel.authError.collectAsState()
            val syllabusVersion by viewModel.syllabusVersion.collectAsState()
            val allLicenses by viewModel.allLicenses.collectAsState()

            MyApplicationTheme(darkTheme = isDarkTheme) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        if (!showAuthScreen) {
                            AppHeaderBar(
                                userEmail = userLicense?.email,
                                expiresAt = userLicense?.expiresAt,
                                selectedTrack = selectedTrack,
                                isDarkTheme = isDarkTheme,
                                onToggleTheme = { viewModel.toggleTheme() },
                                onOpenAdmin = { viewModel.openAdminModal() },
                                onLogout = { viewModel.logout() }
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(innerPadding)
                    ) {
                        if (showAuthScreen) {
                            AuthScreen(
                                errorMessage = authError,
                                onLogin = { email, code -> viewModel.login(email, code) },
                                onStartDemo = { viewModel.start10MinuteDemo() },
                                onOpenAdmin = { viewModel.openAdminModal() }
                            )
                        } else {
                            // Back handler to navigate back to home if inside module view
                            BackHandler(enabled = currentView != "home") {
                                viewModel.switchView("home")
                            }

                            AnimatedContent(
                                targetState = currentView,
                                transitionSpec = { fadeIn() togetherWith fadeOut() },
                                label = "viewTransition"
                            ) { targetView ->
                                if (targetView == "home") {
                                    HomeScreen(
                                        viewModel = viewModel,
                                        onSelectModule = { moduleId -> viewModel.switchView(moduleId) }
                                    )
                                } else {
                                    val modules = viewModel.getModulesForTrack(selectedTrack)
                                    val activeModule = modules.find { it.id == targetView }
                                    if (activeModule != null) {
                                        SubjectModuleScreen(
                                            module = activeModule,
                                            viewModel = viewModel,
                                            onBack = { viewModel.switchView("home") }
                                        )
                                    } else {
                                        HomeScreen(
                                            viewModel = viewModel,
                                            onSelectModule = { moduleId -> viewModel.switchView(moduleId) }
                                        )
                                    }
                                }
                            }
                        }

                        // Admin Dialog
                        if (showAdminModal) {
                            AdminModalDialog(
                                isUnlocked = isAdminUnlocked,
                                adminMessage = adminMessage,
                                generatedCode = generatedAdminCode,
                                allLicenses = allLicenses,
                                onVerifyPasscode = { viewModel.verifyAdminPasscode(it) },
                                onGenerateCode = { email, days, trackId -> viewModel.generateAdminToken(email, days, trackId) },
                                onRevokeCode = { email -> viewModel.revokeClientAccess(email) },
                                onUnblockCode = { email -> viewModel.unblockClientAccess(email) },
                                onDeleteLicense = { email -> viewModel.deleteClientLicense(email) },
                                onDismiss = { viewModel.closeAdminModal() }
                            )
                        }
                    }
                }
            }
        }
    }
}
