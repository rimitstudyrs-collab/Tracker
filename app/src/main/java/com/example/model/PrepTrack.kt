package com.example.model

enum class PrepTrack(
    val id: String,
    val title: String,
    val shortName: String,
    val badge: String,
    val icon: String,
    val description: String,
    val primaryColorHex: Long,
    val targetInstitutions: String
) {
    HSC(
        id = "hsc",
        title = "এইচএসসি পূর্ণাঙ্গ সিলেবাস ট্র্যাকার",
        shortName = "HSC",
        badge = "বোর্ড পরীক্ষা ও অ্যাকাডেমিক",
        icon = "🎓",
        description = "বাংলা, ইংরেজি, আইসিটি ও বিজ্ঞান বিভাগের (পদার্থ, রসায়ন, গণিত, জীববিজ্ঞান) বোর্ড পরীক্ষার রোডম্যাপ।",
        primaryColorHex = 0xFF4F46E5,
        targetInstitutions = "HSC Board Exam A+"
    ),
    VARSITY_A(
        id = "varsity_a",
        title = "ভার্সিটি 'ক' ইউনিট (বিজ্ঞান অনুষদ)",
        shortName = "Varsity A",
        badge = "বিজ্ঞান অনুষদ • ঢাবি ক ও গুচ্ছ এ",
        icon = "🔬",
        description = "পদার্থবিজ্ঞান, রসায়ন, উচ্চতর গণিত, জীববিজ্ঞান, আইসিটি ও বিগত ২০ বছরের বিজ্ঞান প্রশ্নব্যাংক ড্রিল।",
        primaryColorHex = 0xFF0284C7,
        targetInstitutions = "DU A Unit, GST Cluster A, JU, RU, CU"
    ),
    VARSITY_B(
        id = "varsity_b",
        title = "মানবিক ও কলা বিভাগ (ভার্সিটি 'খ' ইউনিট)",
        shortName = "Humanities (খ)",
        badge = "মানবিক ও সামাজিক বিজ্ঞান • DU B & GST",
        icon = "🏛️",
        description = "বাংলা ১ম ও ২য় পত্র, ইংলিশ গ্রামার ও টেক্সটবুক সাহিত্য, বাংলাদেশ ও আন্তর্জাতিক বিষয়াবলী ও মানসিক দক্ষতা।",
        primaryColorHex = 0xFF8B5CF6,
        targetInstitutions = "DU B Unit, GST B, RU A, JU B/C, CU B"
    ),
    VARSITY_C(
        id = "varsity_c",
        title = "ব্যবসায় শিক্ষা বিভাগ (ভার্সিটি 'গ' ইউনিট)",
        shortName = "Business Studies (গ)",
        badge = "ব্যবসায় শিক্ষা ও বাণিজ্য • DU C & GST",
        icon = "📊",
        description = "হিসাববিজ্ঞান ১ম ও ২য় পত্র, ব্যবসায় সংগঠন ও ব্যবস্থাপনা, ফিন্যান্স/মার্কেটিং, বিজনেস ইংলিশ ও বাংলা।",
        primaryColorHex = 0xFF0D9488,
        targetInstitutions = "DU C Unit, GST C, RU B, JU E, CU C"
    ),
    VARSITY_D(
        id = "varsity_d",
        title = "ভার্সিটি 'ঘ' ইউনিট (বিভাগ পরিবর্তন ও আইবিএ)",
        shortName = "Varsity D",
        badge = "বিভাগ পরিবর্তন ও আইবিএ",
        icon = "🎯",
        description = "অ্যাডভান্সড ইংলিশ, বাংলা ব্যাকরণ ও সাহিত্য, বেসিক ম্যাথমেটিক্স, অ্যানালিটিক্যাল রিজনিং ও সাধারণ জ্ঞান।",
        primaryColorHex = 0xFFEC4899,
        targetInstitutions = "DU D Unit, GST, JU IBA, RU, CU Faculty Change"
    ),
    ENGINEERING(
        id = "engineering",
        title = "ইঞ্জিনিয়ারিং ভর্তি প্রস্তুতি ট্র্যাকার",
        shortName = "Engineering",
        badge = "বুয়েট ও গুচ্ছ ইঞ্জিনিয়ারিং",
        icon = "⚙️",
        description = "বুয়েট, সিকেআরইউইটি (চুয়েট, কুয়েট, রুয়েট), বুটেক্স ও এমআইএসটি কনসেপচুয়াল প্রবলেম সলভিং ও প্রশ্নব্যাংক।",
        primaryColorHex = 0xFFD97706,
        targetInstitutions = "BUET, CKREUT, BUTEX, MIST"
    ),
    MEDICAL(
        id = "medical",
        title = "মেডিকেল ভর্তি প্রস্তুতি ট্র্যাকার",
        shortName = "Medical",
        badge = "ডিজিএইচএস ১০০ নম্বরের ভর্তি পরীক্ষা",
        icon = "🩺",
        description = "বোটানি, জুয়োলজি, কেমিস্ট্রি, ফিজিক্স মূল বই লাইন-টু-লাইন দাগিয়ে পড়া, ইংরেজি ও মেডিকেল জিকে রোডম্যাপ।",
        primaryColorHex = 0xFF059669,
        targetInstitutions = "DMC, SSMC, MMC & Govt Medical Colleges"
    )
}
