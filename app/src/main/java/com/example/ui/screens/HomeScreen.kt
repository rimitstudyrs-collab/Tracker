package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ModuleSection
import com.example.model.PrepTrack
import com.example.model.SyllabusData
import com.example.ui.TrackerViewModel
import com.example.ui.components.ProgressBar
import com.example.ui.components.TrackSelectorRow
import com.example.ui.theme.PremiumGold
import androidx.compose.runtime.collectAsState
import com.example.ui.components.DailySummaryCard
import com.example.ui.theme.PremiumGoldHover
import com.example.ui.theme.PremiumGoldMuted

@Composable
fun HomeScreen(
    viewModel: TrackerViewModel,
    onSelectModule: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedTrack = viewModel.selectedTrack.value
    val syllabusVersion = viewModel.syllabusVersion.collectAsState().value
    val overallPercent = viewModel.getOverallTrackProgress()
    val modules = viewModel.getModulesForTrack(selectedTrack)
    val searchQuery = viewModel.searchQuery.value
    val isDemoActive = viewModel.isDemoActive.collectAsState().value
    val demoRemainingSeconds = viewModel.demoRemainingSeconds.collectAsState().value
    val isStudyAlertEnabled = viewModel.isStudyAlertEnabled.collectAsState().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 32.dp)
    ) {
        val allowedTracks = viewModel.getAllowedTracks()

        // Track Switcher Selector Row
        TrackSelectorRow(
            selectedTrack = selectedTrack,
            allowedTracks = allowedTracks,
            onTrackSelected = { viewModel.switchTrack(it) }
        )

        Spacer(modifier = Modifier.height(6.dp))

        // Daily Summary & Study Session Alerts Notification Card
        DailySummaryCard(
            selectedTrack = selectedTrack,
            overallPercent = overallPercent,
            isDemoActive = isDemoActive,
            demoRemainingSeconds = demoRemainingSeconds,
            onStudySessionAlertToggle = { viewModel.toggleStudyAlert() },
            isStudyAlertEnabled = isStudyAlertEnabled
        )

        Spacer(modifier = Modifier.height(10.dp))

        // 1. FULL SYLLABUS PROGRESS HERO BANNER
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .testTag("full_progress_banner"),
            shape = RoundedCornerShape(24.dp),
            color = Color.Transparent,
            tonalElevation = 6.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color(selectedTrack.primaryColorHex),
                                Color(0xFF1E1E1E),
                                Color(0xFF121212)
                            )
                        )
                    )
                    .border(
                        1.dp,
                        PremiumGold.copy(alpha = 0.4f),
                        RoundedCornerShape(24.dp)
                    )
                    .padding(20.dp)
            ) {
                // Background decorative glow
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.TopEnd)
                        .clip(CircleShape)
                        .background(PremiumGold.copy(alpha = 0.08f))
                )

                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(percent = 50))
                                .background(Color.White.copy(alpha = 0.15f))
                                .padding(horizontal = 10.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "OVERALL ROADMAP SUMMARY",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = PremiumGold
                            )
                        }

                        Text(
                            text = selectedTrack.targetInstitutions,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "${selectedTrack.title} Progress",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )

                    Text(
                        text = selectedTrack.description,
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.8f),
                        modifier = Modifier.padding(top = 2.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "$overallPercent%",
                                fontSize = 42.sp,
                                fontWeight = FontWeight.Black,
                                color = PremiumGold
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Completed",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }

                        Text(
                            text = if (overallPercent >= 80) "🔥 Excellent Progress" else if (overallPercent >= 40) "⚡ Consistent Momentum" else "🎯 Begin Preparation",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = PremiumGoldHover
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // TOTAL PREPARATION METER
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "TOTAL PREPARATION METER",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = PremiumGold
                        )
                        Text(
                            text = "$overallPercent% Mastered",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    ProgressBar(
                        progressPercent = overallPercent,
                        height = 10,
                        brush = Brush.horizontalGradient(
                            listOf(
                                PremiumGoldMuted,
                                PremiumGold,
                                PremiumGoldHover
                            )
                        ),
                        backgroundColor = Color.Black.copy(alpha = 0.4f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Module Cards section title
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${selectedTrack.shortName.uppercase()} SUBJECT MODULES",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "${modules.size} Modules Available",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = PremiumGold
                )
            }
            Text(
                text = "Tap any module to view chapters, drive notes & check off tasks",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 2. MODULE CARDS LIST
        modules.forEach { module ->
            val modulePercent = viewModel.getModuleProgress(module)

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .testTag("module_card_${module.id}")
                    .clickable { onSelectModule(module.id) },
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.outline.copy(alpha = 0.35f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(
                                        Brush.linearGradient(
                                            listOf(
                                                Color(module.colorStartHex).copy(alpha = 0.2f),
                                                Color(module.colorEndHex).copy(alpha = 0.4f)
                                            )
                                        )
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = module.iconEmoji, fontSize = 22.sp)
                            }

                            Column(modifier = Modifier.weight(1f, fill = false)) {
                                Text(
                                    text = module.title,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Black,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = module.subtitle,
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 1
                                )
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "$modulePercent%",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Black,
                                color = PremiumGold
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(Color(module.colorStartHex)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = "Open",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    ProgressBar(
                        progressPercent = modulePercent,
                        height = 7,
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(module.colorStartHex),
                                Color(module.colorEndHex)
                            )
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${module.subjects.size} Subjects (${module.subjects.sumOf { it.chapters.size }} Chapters)",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = "$modulePercent% Completed",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Track Research & Strategy Guide Box
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "🎯 ${selectedTrack.title} প্রস্তুতির গাইডলাইন ও স্ট্র্যাটেজি",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                val advice = when (selectedTrack) {
                    PrepTrack.HSC -> "এইচএসসি পরীক্ষায় কাঙ্ক্ষিত এ+ অর্জনের জন্য প্রতিটি বিষয়ের মূল বইয়ের গাণিতিক উদাহরণ এবং বিগত ৫ বছরের বোর্ড সৃজনশীল (CQ) ও বহুনির্বাচনী (MCQ) গভীর মনোযোগে সমাধান করুন।"
                    PrepTrack.VARSITY_A -> "ঢাকা বিশ্ববিদ্যালয় 'ক' ইউনিট ও গুচ্ছ 'এ' ইউনিটে ক্যালকুলেটর ব্যবহারের সুযোগ থাকে না। তাই শর্টকাট টেকনিক, বিগত ২০ বছরের প্রশ্নব্যাংক এবং দ্রুত সঠিক হিসাবের ড্রিল চর্চা করুন।"
                    PrepTrack.VARSITY_B -> "ঢাকা বিশ্ববিদ্যালয় 'খ' ইউনিট ও গুচ্ছ 'বি' ইউনিটে টেক্সটবুক সাহিত্য, ব্যাকরণ, বেসিক গ্রামার রুলস এবং বাংলাদেশ ও আন্তর্জাতিক বিষয়াবলীর সাম্প্রতিক ঘটনায় সর্বোচ্চ গুরুত্ব দিন।"
                    PrepTrack.VARSITY_C -> "ঢাকা বিশ্ববিদ্যালয় 'গ' ইউনিট ও গুচ্ছ 'সি' ইউনিটের জন্য হিসাববিজ্ঞানের জাবেদা-খতিয়ান-আর্থিক বিবরণী, ব্যবসায় সংগঠন, ফিন্যান্স/মার্কেটিং ও বিজনেস ইংলিশ টার্মগুলো প্রতিদিন রিভিশনে রাখুন।"
                    PrepTrack.VARSITY_D -> "ভার্সিটি 'ঘ' ইউনিট ও আইবিএ প্রস্তুতির জন্য অ্যানালিটিক্যাল রিজনিং, ম্যাথমেটিক্যাল প্রবলেম সলভিং, ইংলিশ কম্প্রিহেনশন ও সাধারণ জ্ঞানে সময় ভাগ করে নিয়মিত মক টেস্ট দিন।"
                    PrepTrack.ENGINEERING -> "বুয়েট ও সিকেআরইউইটি ইঞ্জিনিয়ারিং ভর্তির জন্য মুখস্থের বাইরে গিয়ে উচ্চতর গণিত, ক্যালকুলাস ও পদার্থবিজ্ঞানের প্রতিটি ডেরিভেশন ও কনসেপ্ট বুক গভীরভাবে সম্পন্ন করুন।"
                    PrepTrack.MEDICAL -> "মেডিকেল ভর্তি পরীক্ষায় ১ নম্বরের জন্যও লড়াই হয়। আবুল হাসান বোটানি, গাজী আজমল জুয়োলজি ও হাজারী-নাগ কেমিস্ট্রি মূল বই লাইন-টু-লাইন দাগিয়ে ও নেগেটিভ মার্কিং হিসাব করে প্র্যাকটিস করুন।"
                }
                Text(
                    text = advice,
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 16.sp
                )
            }
        }
    }
}
