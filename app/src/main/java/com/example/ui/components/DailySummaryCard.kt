package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.PrepTrack
import com.example.ui.theme.PremiumGold
import com.example.ui.theme.PremiumGoldHover
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun launchWhatsApp(
    context: Context,
    phone: String = "8801727328822",
    message: String = "আসসালামু আলাইকুম, আমি HSC & Admission Tracker অ্যাপের ফুল এক্সেস কোড নিতে চাই।"
) {
    try {
        val uri = Uri.parse("https://wa.me/$phone?text=${Uri.encode(message)}")
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "WhatsApp খুলতে সমস্যা হয়েছে: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun DailySummaryCard(
    selectedTrack: PrepTrack,
    overallPercent: Int,
    isDemoActive: Boolean,
    demoRemainingSeconds: Long,
    onStudySessionAlertToggle: () -> Unit,
    isStudyAlertEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val todayDate = remember {
        val sdf = SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH)
        sdf.format(Date())
    }

    var dailyGoalDone by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .testTag("daily_summary_card"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, PremiumGold.copy(alpha = 0.35f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            // Header: Date & Notification Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = "Date",
                        tint = PremiumGold,
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = todayDate,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (isStudyAlertEnabled) Color(0xFF10B981).copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant,
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (isStudyAlertEnabled) Color(0xFF10B981) else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                    ),
                    modifier = Modifier.clickable { onStudySessionAlertToggle() }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = if (isStudyAlertEnabled) Icons.Default.NotificationsActive else Icons.Default.Alarm,
                            contentDescription = "Alerts",
                            tint = if (isStudyAlertEnabled) Color(0xFF10B981) else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(13.dp)
                        )
                        Text(
                            text = if (isStudyAlertEnabled) "স্টাডি এলার্ট অন" else "এলার্ট সেট করুন",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isStudyAlertEnabled) Color(0xFF10B981) else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Demo Warning & WhatsApp Banner if demo is active
            if (isDemoActive) {
                val mins = demoRemainingSeconds / 60
                val secs = demoRemainingSeconds % 60
                val timeFormatted = String.format(Locale.US, "%02d:%02d", mins, secs)

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    shape = RoundedCornerShape(14.dp),
                    color = Color(0xFFDC2626).copy(alpha = 0.12f),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFDC2626).copy(alpha = 0.4f))
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "⏳ ডেমো ট্রায়াল: $timeFormatted বাকি",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Black,
                                color = Color(0xFFEF4444)
                            )
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(Color(0xFFDC2626))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "১০ মিনিট মেয়াদ",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "মেয়াদ শেষ হওয়ার পর পূর্বের ডাটা মুছে যাবে। ফুল ভার্সন নিতে সরাসরি হোয়াটসঅ্যাপে মেসেজ দিন:",
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 15.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = { launchWhatsApp(context) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(38.dp)
                                .testTag("whatsapp_contact_demo_btn"),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF25D366),
                                contentColor = Color.White
                            )
                        ) {
                            Icon(Icons.Default.Chat, contentDescription = null, modifier = Modifier.size(15.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("হোয়াটসঅ্যাপে মেসেজ দিন: 01727328822", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // High Priority Session & Target
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Priority Focus Box
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(14.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f), RoundedCornerShape(14.dp))
                        .padding(12.dp)
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.PriorityHigh,
                                contentDescription = null,
                                tint = PremiumGold,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "হাই-প্রায়োরিটি ফোকাস",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = PremiumGold
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        val focusText = when (selectedTrack) {
                            PrepTrack.HSC -> "PCMB ম্যাথ ও ফিজিক্স CQ প্র্যাকটিস"
                            PrepTrack.VARSITY_A -> "DU বিগত ২০ বছরের প্রশ্নব্যাংক ড্রিল"
                            PrepTrack.VARSITY_B -> "বাংলা, ইংরেজি ও সাধারণ জ্ঞান রিভিশন"
                            PrepTrack.VARSITY_C -> "ব্যবসায় সংগঠন, ফিন্যান্স ও মার্কেটিং ড্রিল"
                            PrepTrack.VARSITY_D -> "অ্যানালিটিক্যাল অ্যাবিলিটি ও জিকে প্র্যাকটিস"
                            PrepTrack.ENGINEERING -> "বুয়েট কনসেপ্ট বুক ও ক্যালকুলাস"
                            PrepTrack.MEDICAL -> "বোটানি ও জুয়োলজি লাইন-টু-লাইন রিভিশন"
                        }
                        Text(
                            text = focusText,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 15.sp
                        )
                    }
                }

                // Daily Goal Checklist Box
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(14.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f), RoundedCornerShape(14.dp))
                        .clickable { dailyGoalDone = !dailyGoalDone }
                        .padding(12.dp)
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = if (dailyGoalDone) Color(0xFF10B981) else MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = if (dailyGoalDone) "আজকের টার্গেট সম্পন্ন!" else "আজকের টার্গেট",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (dailyGoalDone) Color(0xFF10B981) else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (dailyGoalDone) "🔥 অসাধারণ! আজকের স্টাডি মাইলফলক পূরণ হয়েছে।" else "🎯 আজ অন্তত ২টি অধ্যায়ের প্রশ্নব্যাংক শেষ করুন",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 15.sp
                        )
                    }
                }
            }

            // WhatsApp Help & Full Version Purchase bar
            Spacer(modifier = Modifier.height(10.dp))
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { launchWhatsApp(context) },
                shape = RoundedCornerShape(12.dp),
                color = PremiumGold.copy(alpha = 0.1f),
                border = androidx.compose.foundation.BorderStroke(1.dp, PremiumGold.copy(alpha = 0.3f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(text = "💬", fontSize = 14.sp)
                        Column {
                            Text(
                                text = "ফুল ভার্সন বা লাইসেন্স কোড লাগলে:",
                                fontSize = 10.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "WhatsApp: 01727328822 (মেসেজ দিতে ট্যাপ করুন)",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = PremiumGold
                            )
                        }
                    }
                    Text(
                        text = "পাঠান ↗",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = PremiumGold
                    )
                }
            }
        }
    }
}
