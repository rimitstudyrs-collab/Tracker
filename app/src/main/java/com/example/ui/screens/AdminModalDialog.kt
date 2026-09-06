package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Security
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.UserLicenseEntity
import com.example.ui.theme.PremiumGold
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatTrackDisplay(trackStr: String): String {
    if (trackStr == "all") return "🌟 সকল কোর্স (Full Access)"
    val parts = trackStr.split(",").map { it.trim().lowercase(Locale.ROOT) }.filter { it.isNotEmpty() }
    val names = parts.mapNotNull { id ->
        when (id) {
            "varsity_a" -> "Varsity 'A' (বিজ্ঞান)"
            "varsity_b" -> "Humanities / মানবিক (Varsity 'B')"
            "varsity_c" -> "Business Studies / ব্যবসায় শিক্ষা (Varsity 'C')"
            "varsity_d" -> "Varsity 'D' (বিভাগ পরিবর্তন ও IBA)"
            "engineering" -> "ইঞ্জিনিয়ারিং"
            "medical" -> "মেডিকেল"
            "hsc" -> "HSC বোর্ড"
            else -> id.uppercase(Locale.ROOT)
        }
    }
    return if (names.isNotEmpty()) names.joinToString(" + ") else "কাস্টম এক্সেস"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminModalDialog(
    isUnlocked: Boolean,
    adminMessage: String?,
    generatedCode: String?,
    allLicenses: List<UserLicenseEntity> = emptyList(),
    onVerifyPasscode: (String) -> Boolean,
    onGenerateCode: (email: String, days: Int, trackId: String) -> Unit,
    onRevokeCode: (email: String) -> Unit,
    onUnblockCode: (email: String) -> Unit,
    onDeleteLicense: (email: String) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var passcode by remember { mutableStateOf("") }
    var targetEmail by remember { mutableStateOf("") }
    var validityDays by remember { mutableIntStateOf(30) }
    var customValidityInput by remember { mutableStateOf("30") }

    // Multi-track customization state (Supports individual tracks, combos like Varsity A+B, Engg+Med, etc.)
    var isAllTracksSelected by remember { mutableStateOf(false) }
    var selectedTrackIds by remember { mutableStateOf(setOf("varsity_a")) }

    val trackDefinitions = listOf(
        "varsity_c" to ("Business Studies (গ)" to "📊 ব্যবসায় শিক্ষা অনুষদ (Accounting, Management, Finance)"),
        "varsity_b" to ("Humanities (খ)" to "🏛️ মানবিক ও কলা অনুষদ (Bangla, English, GK, ICT)"),
        "varsity_a" to ("Varsity A (ক)" to "🔬 বিজ্ঞান অনুষদ (DU Ka & GST A)"),
        "varsity_d" to ("Varsity D (ঘ)" to "🎯 বিভাগ পরিবর্তন ও IBA (Analytical, Math, English)"),
        "engineering" to ("Engineering" to "⚙️ বুয়েট ও ইঞ্জিনিয়ারিং (BUET, CKREUT)"),
        "medical" to ("Medical" to "🩺 মেডিকেল ও ডেন্টাল (DMC, Govt Medical)"),
        "hsc" to ("HSC Academic" to "🎓 এইচএসসি বোর্ড সিলেবাস (A+ Goal)")
    )

    val validityPresets = listOf(
        7 to "৭ দিন",
        15 to "১৫ দিন",
        30 to "৩০ দিন (১ মাস)",
        60 to "৬০ দিন (২ মাস)",
        90 to "৯০ দিন (৩ মাস)",
        180 to "১৮০ দিন (৬ মাস)",
        365 to "৩৬৫ দিন (১ বছর)"
    )

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 680.dp)
                .padding(6.dp),
            shape = RoundedCornerShape(24.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp,
            border = androidx.compose.foundation.BorderStroke(1.dp, PremiumGold.copy(alpha = 0.4f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(18.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Security,
                            contentDescription = "Admin",
                            tint = PremiumGold,
                            modifier = Modifier.size(22.dp)
                        )
                        Text(
                            text = "Admin Control Panel",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    IconButton(onClick = onDismiss) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                if (!isUnlocked) {
                    // Passcode Screen
                    Text(
                        text = "Enter Admin Passcode to manage courses and tokens:",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = passcode,
                        onValueChange = { passcode = it },
                        label = { Text("Admin Passcode") },
                        placeholder = { Text("••••••••") },
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = null, tint = PremiumGold)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("admin_passcode_input"),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PremiumGold,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        )
                    )

                    if (!adminMessage.isNullOrBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = adminMessage,
                            color = MaterialTheme.colorScheme.error,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { onVerifyPasscode(passcode) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("verify_admin_btn"),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PremiumGold, contentColor = Color(0xFF121212))
                    ) {
                        Text("Open Token Generator", fontWeight = FontWeight.Bold)
                    }
                } else {
                    // Unlocked Admin Panel
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(PremiumGold.copy(alpha = 0.1f))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "নির্দিষ্ট কোর্সের জন্য কোড তৈরি করুন। শিক্ষার্থী লগইন করলে শুধুমাত্র ওই নির্ধারিত কোর্সটিই দেখতে পাবে। সময় শেষের আগেও যেকোনো সময় ব্লক করতে পারবেন।",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = targetEmail,
                        onValueChange = { targetEmail = it },
                        label = { Text("Student Gmail") },
                        placeholder = { Text("student@gmail.com") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("admin_client_email_input"),
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Track Selection Section (Multi-Select Supported)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
                            .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f), RoundedCornerShape(14.dp))
                            .padding(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "🎯 Track Access (Multi-Select Allowed)",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            val count = if (isAllTracksSelected || selectedTrackIds.size == trackDefinitions.size) {
                                "All (7)"
                            } else {
                                "${selectedTrackIds.size} Selected"
                            }
                            Text(
                                text = count,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = PremiumGold
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        // Quick Combo Presets
                        Text(
                            text = "QUICK COMBOS:",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            val isCommerceOnly = !isAllTracksSelected && selectedTrackIds == setOf("varsity_c")
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isCommerceOnly) PremiumGold else MaterialTheme.colorScheme.surface,
                                border = androidx.compose.foundation.BorderStroke(1.dp, PremiumGold.copy(alpha = 0.6f)),
                                modifier = Modifier.clickable {
                                    isAllTracksSelected = false
                                    selectedTrackIds = setOf("varsity_c")
                                }
                            ) {
                                Text(
                                    text = "📊 Business Studies (গ)",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isCommerceOnly) Color.Black else MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                                )
                            }

                            val isHumanitiesOnly = !isAllTracksSelected && selectedTrackIds == setOf("varsity_b")
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isHumanitiesOnly) PremiumGold else MaterialTheme.colorScheme.surface,
                                border = androidx.compose.foundation.BorderStroke(1.dp, PremiumGold.copy(alpha = 0.6f)),
                                modifier = Modifier.clickable {
                                    isAllTracksSelected = false
                                    selectedTrackIds = setOf("varsity_b")
                                }
                            ) {
                                Text(
                                    text = "🏛️ Humanities (খ)",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isHumanitiesOnly) Color.Black else MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                                )
                            }

                            val isArtsComm = !isAllTracksSelected && selectedTrackIds == setOf("varsity_b", "varsity_c")
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isArtsComm) PremiumGold else MaterialTheme.colorScheme.surface,
                                border = androidx.compose.foundation.BorderStroke(1.dp, PremiumGold.copy(alpha = 0.6f)),
                                modifier = Modifier.clickable {
                                    isAllTracksSelected = false
                                    selectedTrackIds = setOf("varsity_b", "varsity_c")
                                }
                            ) {
                                Text(
                                    text = "📚 Arts + Commerce",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isArtsComm) Color.Black else MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                                )
                            }

                            val isVarsityAB = !isAllTracksSelected && selectedTrackIds == setOf("varsity_a", "varsity_b")
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isVarsityAB) PremiumGold else MaterialTheme.colorScheme.surface,
                                border = androidx.compose.foundation.BorderStroke(1.dp, PremiumGold.copy(alpha = 0.6f)),
                                modifier = Modifier.clickable {
                                    isAllTracksSelected = false
                                    selectedTrackIds = setOf("varsity_a", "varsity_b")
                                }
                            ) {
                                Text(
                                    text = "🏛️ Varsity A + B",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isVarsityAB) Color.Black else MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                                )
                            }

                            val isEnggMed = !isAllTracksSelected && selectedTrackIds == setOf("engineering", "medical")
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isEnggMed) PremiumGold else MaterialTheme.colorScheme.surface,
                                border = androidx.compose.foundation.BorderStroke(1.dp, PremiumGold.copy(alpha = 0.6f)),
                                modifier = Modifier.clickable {
                                    isAllTracksSelected = false
                                    selectedTrackIds = setOf("engineering", "medical")
                                }
                            ) {
                                Text(
                                    text = "⚙️ Engg + Med",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isEnggMed) Color.Black else MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                                )
                            }

                            val isVarsityAll = !isAllTracksSelected && selectedTrackIds == setOf("varsity_a", "varsity_b", "varsity_c", "varsity_d")
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isVarsityAll) PremiumGold else MaterialTheme.colorScheme.surface,
                                border = androidx.compose.foundation.BorderStroke(1.dp, PremiumGold.copy(alpha = 0.6f)),
                                modifier = Modifier.clickable {
                                    isAllTracksSelected = false
                                    selectedTrackIds = setOf("varsity_a", "varsity_b", "varsity_c", "varsity_d")
                                }
                            ) {
                                Text(
                                    text = "🎓 Varsity A-D",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isVarsityAll) Color.Black else MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                                )
                            }

                            val isFullVip = isAllTracksSelected || selectedTrackIds.size == trackDefinitions.size
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isFullVip) PremiumGold else MaterialTheme.colorScheme.surface,
                                border = androidx.compose.foundation.BorderStroke(1.dp, PremiumGold.copy(alpha = 0.6f)),
                                modifier = Modifier.clickable {
                                    isAllTracksSelected = true
                                    selectedTrackIds = trackDefinitions.map { it.first }.toSet()
                                }
                            ) {
                                Text(
                                    text = "🌟 Full VIP Access",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isFullVip) Color.Black else MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Individual Toggle Matrix
                        Text(
                            text = "CUSTOMIZE SPECIFIC PROGRAMS:",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            trackDefinitions.forEach { (id, pair) ->
                                val (name, desc) = pair
                                val isChecked = isAllTracksSelected || selectedTrackIds.contains(id)

                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (isChecked) Color(0xFF10B981).copy(alpha = 0.12f) else MaterialTheme.colorScheme.surface,
                                    border = androidx.compose.foundation.BorderStroke(
                                        1.dp,
                                        if (isChecked) Color(0xFF10B981) else MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            if (isAllTracksSelected) {
                                                isAllTracksSelected = false
                                                selectedTrackIds = trackDefinitions.map { it.first }.toSet() - id
                                            } else {
                                                selectedTrackIds = if (selectedTrackIds.contains(id)) {
                                                    val newSet = selectedTrackIds - id
                                                    if (newSet.isEmpty()) setOf("varsity_a") else newSet
                                                } else {
                                                    selectedTrackIds + id
                                                }
                                                if (selectedTrackIds.size == trackDefinitions.size) {
                                                    isAllTracksSelected = true
                                                }
                                            }
                                        }
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 10.dp, vertical = 6.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column {
                                            Text(
                                                text = name,
                                                fontSize = 12.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.onSurface
                                            )
                                            Text(
                                                text = desc,
                                                fontSize = 9.sp,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .clip(RoundedCornerShape(4.dp))
                                                .background(if (isChecked) Color(0xFF10B981) else MaterialTheme.colorScheme.surfaceVariant),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            if (isChecked) {
                                                Icon(
                                                    imageVector = Icons.Default.Check,
                                                    contentDescription = null,
                                                    tint = Color.White,
                                                    modifier = Modifier.size(14.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Validity Selection & Customizer Section
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
                            .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f), RoundedCornerShape(14.dp))
                            .padding(12.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "🕒 এক্সেস মেয়াদ নির্ধারণ (Validity Duration)",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Surface(
                                shape = RoundedCornerShape(6.dp),
                                color = PremiumGold.copy(alpha = 0.18f)
                            ) {
                                Text(
                                    text = "$validityDays দিন এক্সেস",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PremiumGold,
                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Quick Presets Chips
                        Text(
                            text = "কুইক প্রিসেট থেকে বেছে নিন:",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            validityPresets.forEach { (days, label) ->
                                val isSelected = validityDays == days
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = if (isSelected) PremiumGold else MaterialTheme.colorScheme.surface,
                                    border = androidx.compose.foundation.BorderStroke(
                                        1.dp,
                                        if (isSelected) PremiumGold else MaterialTheme.colorScheme.outline.copy(alpha = 0.4f)
                                    ),
                                    modifier = Modifier.clickable {
                                        validityDays = days
                                        customValidityInput = days.toString()
                                    }
                                ) {
                                    Text(
                                        text = label,
                                        fontSize = 11.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) Color(0xFF121212) else MaterialTheme.colorScheme.onSurface,
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Custom Days Numeric Input
                        OutlinedTextField(
                            value = customValidityInput,
                            onValueChange = { input ->
                                val filtered = input.filter { it.isDigit() }.take(5)
                                customValidityInput = filtered
                                val parsed = filtered.toIntOrNull()
                                if (parsed != null && parsed > 0) {
                                    validityDays = parsed
                                }
                            },
                            label = { Text("অথবা কাস্টম দিন সংখ্যা লিখুন (Customize Days)") },
                            placeholder = { Text("যেমন: 5, 12, 45, 100, 365...") },
                            leadingIcon = {
                                Icon(Icons.Default.DateRange, contentDescription = null, tint = PremiumGold)
                            },
                            trailingIcon = {
                                Text(
                                    text = "দিন",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PremiumGold,
                                    modifier = Modifier.padding(end = 12.dp)
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("admin_custom_validity_input"),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PremiumGold,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            )
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "💡 যেকোনো দিন সংখ্যা (যেমন ৫ দিন, ৪৫ দিন বা ১২০ দিন) টাইপ করতে পারবেন। অ্যাক্টিভেশনের দিন থেকে পরবর্তী $validityDays দিন এক্সেস চালু থাকবে।",
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Action Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = {
                                val finalTrackParam = if (isAllTracksSelected || selectedTrackIds.size == trackDefinitions.size) {
                                    "all"
                                } else {
                                    selectedTrackIds.joinToString(",")
                                }
                                onGenerateCode(targetEmail, validityDays, finalTrackParam)
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                                .testTag("admin_generate_btn"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981), contentColor = Color.White)
                        ) {
                            Text("☁️ Create Code", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }

                        OutlinedButton(
                            onClick = { onRevokeCode(targetEmail) },
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                                .testTag("admin_revoke_btn"),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error)
                        ) {
                            Text("🚫 Block Access", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Concurrent Capacity & Real-Time Blocking Info Card
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(text = "⚡", fontSize = 14.sp)
                                Text(
                                    text = "কনকারেন্ট ইউজার ক্যাপাসিটি ও ব্লকিং সিস্টেম",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "• একসাথে কতজন চালাতে পারবে: কোন লিমিট নেই! হাজার হাজার শিক্ষার্থী নিজস্ব এক্সেস কোড দিয়ে একই সাথে কোনো প্রকার কনফ্লিক্ট ছাড়া ব্যবহার করতে পারবে।\n• ব্লকিং পাওয়ার: যেকোনো শিক্ষার্থীর এক্সেস অ্যাডমিন প্যানেল থেকে ১-ক্লিকেই Block বা Unblock করা যায়।",
                                fontSize = 10.sp,
                                lineHeight = 14.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    if (!adminMessage.isNullOrBlank()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = adminMessage,
                            color = if (adminMessage.startsWith("🚫") || adminMessage.startsWith("⚠️") || adminMessage.startsWith("❌")) MaterialTheme.colorScheme.error else Color(0xFF10B981),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    // Generated Code Box
                    if (!generatedCode.isNullOrBlank()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                            shape = RoundedCornerShape(14.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, PremiumGold)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "CLIENT ACCESS CODE",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = generatedCode,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Black,
                                    fontFamily = FontFamily.Monospace,
                                    color = PremiumGold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Button(
                                    onClick = {
                                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                        val clip = ClipData.newPlainText("Access Code", generatedCode)
                                        clipboard.setPrimaryClip(clip)
                                        Toast.makeText(context, "Code copied to clipboard!", Toast.LENGTH_SHORT).show()
                                    },
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = PremiumGold, contentColor = Color(0xFF121212))
                                ) {
                                    Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.size(6.dp))
                                    Text("Copy Code", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }

                    // Student Licenses & Management List
                    val activeLicenses = allLicenses.filter { !it.accessCode.startsWith("DEMO") }
                    if (activeLicenses.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(18.dp))
                        HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "📋 All Registered Students (${activeLicenses.size})",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Instant Block / Unblock",
                                fontSize = 10.sp,
                                color = PremiumGold
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            activeLicenses.forEach { license ->
                                val isBlocked = !license.isActive
                                val daysLeft = ((license.expiresAt - System.currentTimeMillis()) / (1000 * 60 * 60 * 24)).coerceAtLeast(0)
                                val courseName = formatTrackDisplay(license.selectedTrack)

                                Surface(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
                                    border = androidx.compose.foundation.BorderStroke(
                                        1.dp,
                                        if (isBlocked) MaterialTheme.colorScheme.error.copy(alpha = 0.5f) else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                                            ) {
                                                Text(
                                                    text = license.email,
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = MaterialTheme.colorScheme.onSurface
                                                )
                                                Surface(
                                                    shape = RoundedCornerShape(4.dp),
                                                    color = if (isBlocked) MaterialTheme.colorScheme.error else Color(0xFF10B981)
                                                ) {
                                                    Text(
                                                        text = if (isBlocked) "BLOCKED" else "ACTIVE",
                                                        fontSize = 8.sp,
                                                        fontWeight = FontWeight.Bold,
                                                        color = Color.White,
                                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                                    )
                                                }
                                            }
                                            Spacer(modifier = Modifier.height(3.dp))
                                            Text(
                                                text = "🎯 $courseName  •  🔑 ${license.accessCode}",
                                                fontSize = 10.sp,
                                                fontFamily = FontFamily.Monospace,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                            Text(
                                                text = "⏳ ${daysLeft}d left (Exp: ${sdf.format(Date(license.expiresAt))})",
                                                fontSize = 10.sp,
                                                color = if (daysLeft <= 3) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }

                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            if (isBlocked) {
                                                IconButton(
                                                    onClick = { onUnblockCode(license.email) },
                                                    modifier = Modifier.size(32.dp)
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.CheckCircle,
                                                        contentDescription = "Unblock",
                                                        tint = Color(0xFF10B981),
                                                        modifier = Modifier.size(18.dp)
                                                    )
                                                }
                                            } else {
                                                IconButton(
                                                    onClick = { onRevokeCode(license.email) },
                                                    modifier = Modifier.size(32.dp)
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.Block,
                                                        contentDescription = "Block",
                                                        tint = MaterialTheme.colorScheme.error,
                                                        modifier = Modifier.size(18.dp)
                                                    )
                                                }
                                            }
                                            IconButton(
                                                onClick = { onDeleteLicense(license.email) },
                                                modifier = Modifier.size(32.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Delete,
                                                    contentDescription = "Delete",
                                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                                    modifier = Modifier.size(18.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
