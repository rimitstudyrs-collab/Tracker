package com.example.model

object SyllabusData {

    // --- SHARED DETAILED CHAPTER LISTS FOR COMPLETE RESEARCH ---

    private val physics1Chapters = listOf(
        Chapter("১. ভৌতজগৎ ও পরিমাপ (Physical World & Measurement)", TaskSetType.STANDARD),
        Chapter("২. ভেক্টর (Vectors)", TaskSetType.STANDARD),
        Chapter("৩. গতিবিদ্যা (Dynamics & Kinematics)", TaskSetType.STANDARD),
        Chapter("৪. নিউটনীয় বলবিদ্যা (Newtonian Mechanics)", TaskSetType.STANDARD),
        Chapter("৫. কাজ, শক্তি ও ক্ষমতা (Work, Energy & Power)", TaskSetType.STANDARD),
        Chapter("৬. মহাকর্ষ ও অভিকর্ষ (Gravitation & Gravity)", TaskSetType.STANDARD),
        Chapter("৭. পদার্থের গাঠনিক ধর্ম (Structural Properties of Matter)", TaskSetType.STANDARD),
        Chapter("৮. পর্যায়বৃত্ত গতি (Periodic Motion)", TaskSetType.STANDARD),
        Chapter("৯. তরঙ্গ (Waves)", TaskSetType.STANDARD),
        Chapter("১০. আদর্শ গ্যাস ও গ্যাসের গতিতত্ত্ব (Ideal Gas & Kinetic Theory)", TaskSetType.STANDARD)
    )

    private val physics2Chapters = listOf(
        Chapter("১. তাপগতিবিদ্যা (Thermodynamics)", TaskSetType.STANDARD),
        Chapter("২. স্থির তড়িৎ (Electrostatics)", TaskSetType.STANDARD),
        Chapter("৩. চল তড়িৎ (Current Electricity)", TaskSetType.STANDARD),
        Chapter("৪. তড়িৎ প্রবাহের চৌম্বক ক্রিয়া ও চুম্বকত্ব (Magnetic Effects of Current)", TaskSetType.STANDARD),
        Chapter("৫. তাড়িতচৌম্বকীয় আবেশ ও পরিবর্তী প্রবাহ (Electromagnetic Induction & AC)", TaskSetType.STANDARD),
        Chapter("৬. জ্যামিতিক আলোকবিজ্ঞান (Geometrical Optics)", TaskSetType.STANDARD),
        Chapter("৭. ভৌত আলোকবিজ্ঞান (Physical Optics)", TaskSetType.STANDARD),
        Chapter("৮. আধুনিক পদার্থবিজ্ঞানের সূচনা (Modern Physics)", TaskSetType.STANDARD),
        Chapter("৯. পরমাণুর মডেল এবং নিউক্লিয়ার পদার্থবিজ্ঞান (Atomic Model & Nuclear Physics)", TaskSetType.STANDARD),
        Chapter("১০. সেমিকন্ডাক্টর ও ইলেকট্রনিক্স (Semiconductor & Electronics)", TaskSetType.STANDARD),
        Chapter("১১. জ্যোতির্বিজ্ঞান (Astronomy)", TaskSetType.STANDARD)
    )

    private val chemistry1Chapters = listOf(
        Chapter("১. ল্যাবরেটরির নিরাপদ ব্যবহার (Safe Use of Laboratory)", TaskSetType.STANDARD),
        Chapter("২. গুণগত রসায়ন (Qualitative Chemistry)", TaskSetType.STANDARD),
        Chapter("৩. মৌলের পর্যায়বৃত্ত ধর্ম ও রাসায়নিক বন্ধন (Periodic Properties & Bonding)", TaskSetType.STANDARD),
        Chapter("৪. রাসায়নিক পরিবর্তন (Chemical Changes & Equilibrium)", TaskSetType.STANDARD),
        Chapter("৫. কর্মমুখী রসায়ন (Applied Chemistry)", TaskSetType.STANDARD)
    )

    private val chemistry2Chapters = listOf(
        Chapter("১. পরিবেশ রসায়ন (Environmental Chemistry)", TaskSetType.STANDARD),
        Chapter("২. জৈব রসায়ন (Organic Chemistry)", TaskSetType.STANDARD),
        Chapter("৩. পরিমাণগত রসায়ন (Quantitative Chemistry)", TaskSetType.STANDARD),
        Chapter("৪. তড়িৎ রসায়ন (Electrochemistry)", TaskSetType.STANDARD),
        Chapter("৫. অর্থনৈতিক রসায়ন (Economic Chemistry)", TaskSetType.STANDARD)
    )

    private val higherMath1Chapters = listOf(
        Chapter("১. ম্যাট্রিক্স ও নির্ণায়ক (Matrices & Determinants)", TaskSetType.STANDARD),
        Chapter("২. ভেক্টর (Vectors)", TaskSetType.STANDARD),
        Chapter("৩. সরলরেখা (Straight Lines)", TaskSetType.STANDARD),
        Chapter("৪. বৃত্ত (Circles)", TaskSetType.STANDARD),
        Chapter("৫. বিন্যাস ও সমাবেশ (Permutations & Combinations)", TaskSetType.STANDARD),
        Chapter("৬. ত্রিকোণমিতিক অনুপাত (Trigonometric Ratios)", TaskSetType.STANDARD),
        Chapter("৭. সংযুক্ত কোণের ত্রিকোণমিতিক অনুপাত (Associated Angles Trigonometry)", TaskSetType.STANDARD),
        Chapter("৮. ফাংশন ও ফাংশনের লেখচিত্র (Functions & Graphs)", TaskSetType.STANDARD),
        Chapter("৯. অন্তরীকরণ (Differentiation / Calculus)", TaskSetType.STANDARD),
        Chapter("১০. যোগজীকরণ (Integration / Calculus)", TaskSetType.STANDARD)
    )

    private val higherMath2Chapters = listOf(
        Chapter("১. বাস্তব সংখ্যা ও অসমতা (Real Numbers & Inequalities)", TaskSetType.STANDARD),
        Chapter("২. যোগাশ্রয়ী প্রোগ্রাম (Linear Programming)", TaskSetType.STANDARD),
        Chapter("৩. জটিল সংখ্যা (Complex Numbers)", TaskSetType.STANDARD),
        Chapter("৪. বহুপদী ও বহুপদী সমীকরণ (Polynomials & Equations)", TaskSetType.STANDARD),
        Chapter("৫. দ্বিপদী বিস্তার (Binomial Expansion)", TaskSetType.STANDARD),
        Chapter("৬. কণিক (Conics - Parabola, Ellipse, Hyperbola)", TaskSetType.STANDARD),
        Chapter("৭. বিপরীত ত্রিকোণমিতিক ফাংশন ও ত্রিকোণমিতিক সমীকরণ (Inverse Trigonometric Functions)", TaskSetType.STANDARD),
        Chapter("৮. স্থিতিবিদ্যা (Statics)", TaskSetType.STANDARD),
        Chapter("৯. সমতলে বস্তুকণার গতি (Dynamics of Particles)", TaskSetType.STANDARD),
        Chapter("১০. বিস্তার পরিমাপ ও সম্ভাবনা (Measures of Dispersion & Probability)", TaskSetType.STANDARD)
    )

    private val botanyChapters = listOf(
        Chapter("১. কোষ ও এর গঠন (Cell and Its Structure)", TaskSetType.STANDARD),
        Chapter("২. কোষ বিভাজন (Cell Division - Mitosis & Meiosis)", TaskSetType.STANDARD),
        Chapter("৩. কোষ রসায়ন (Cell Chemistry - Carbohydrates, Lipids, Proteins)", TaskSetType.STANDARD),
        Chapter("৪. অণুজীব (Microorganisms - Virus, Bacteria, Malaria Parasite)", TaskSetType.STANDARD),
        Chapter("৫. শৈবাল ও ছত্রাক (Algae and Fungi)", TaskSetType.STANDARD),
        Chapter("৬. ব্রায়োফাইটা ও টেরিডোফাইটা (Bryophyta and Pteridophyta)", TaskSetType.STANDARD),
        Chapter("৭. নগ্নবীজী ও আবৃতবীজী উদ্ভিদ (Gymnosperms and Angiosperms)", TaskSetType.STANDARD),
        Chapter("৮. টিস্যু ও টিস্যুতন্ত্র (Tissue and Tissue Systems)", TaskSetType.STANDARD),
        Chapter("৯. উদ্ভিদ শারীরতত্ত্ব (Plant Physiology - Photosynthesis, Respiration)", TaskSetType.STANDARD),
        Chapter("১০. উদ্ভিদ প্রজনন (Plant Reproduction)", TaskSetType.STANDARD),
        Chapter("১১. জীবপ্রযুক্তি (Biotechnology - Tissue Culture, Genetic Engg)", TaskSetType.STANDARD),
        Chapter("১২. জীবের পরিবেশ, বিস্তার ও সংরক্ষণ (Ecology & Biodiversity)", TaskSetType.STANDARD)
    )

    private val zoologyChapters = listOf(
        Chapter("১. প্রাণীর বিভিন্নতা ও শ্রেণিবিন্যাস (Animal Diversity & Classification)", TaskSetType.STANDARD),
        Chapter("২. প্রাণীর পরিচিতি - হাইড্রা, ঘাসফড়িং ও রুই মাছ (Hydra, Grasshopper & Rohu)", TaskSetType.STANDARD),
        Chapter("৩. মানব শারীরতত্ত্ব: পরিপাক ও শোষণ (Human Digestion & Absorption)", TaskSetType.STANDARD),
        Chapter("৪. মানব শারীরতত্ত্ব: রক্ত ও সংবহন (Blood & Circulation)", TaskSetType.STANDARD),
        Chapter("৫. মানব শারীরতত্ত্ব: শ্বাসক্রিয়া ও শ্বসন (Respiration & Gas Exchange)", TaskSetType.STANDARD),
        Chapter("৬. মানব শারীরতত্ত্ব: বর্জ্য ও নিষ্কাশন (Excretion & Osmoregulation)", TaskSetType.STANDARD),
        Chapter("৭. মানব শারীরতত্ত্ব: চলন ও অঙ্গচালনা (Locomotion & Movement)", TaskSetType.STANDARD),
        Chapter("৮. মানব শারীরতত্ত্ব: সমন্বয় ও নিয়ন্ত্রণ (Coordination & Hormones)", TaskSetType.STANDARD),
        Chapter("৯. মানব জীবনের ধারাবাহিকতা (Human Reproduction & Development)", TaskSetType.STANDARD),
        Chapter("১০. মানবদেহের প্রতিরক্ষা - ইমিউনিটি (Human Immune Defense)", TaskSetType.STANDARD),
        Chapter("১১. জিনতত্ত্ব ও বিবর্তন (Genetics & Evolution - Mendel's Laws)", TaskSetType.STANDARD),
        Chapter("১২. প্রাণীর আচরণ (Animal Behavior - Instinct & Learning)", TaskSetType.STANDARD)
    )

    // --- 1. HSC TRACK MODULES ---
    val hscEbiSubjects = listOf(
        Subject(
            name = "Bangla 1st Paper",
            category = "EBI",
            colorStartHex = 0xFFEF4444,
            colorEndHex = 0xFFE11D48,
            iconEmoji = "📖",
            chapters = listOf(
                Chapter("[গল্প] ১. বাংলার নব্য লেখকদের প্রতি নিবেদন - বঙ্কিমচন্দ্র চট্টোপাধ্যায়", TaskSetType.STANDARD, "https://drive.google.com/file/d/1-H6K7E_s7bNGtIX0qs06Vn5QhoqOhyiL/view?usp=drivesdk"),
                Chapter("[গল্প] ২. অপরিচিতা - রবীন্দ্রনাথ ঠাকুর", TaskSetType.STANDARD),
                Chapter("[গল্প] ৩. সাহিত্যে খেলা - প্রমথ চৌধুরী", TaskSetType.STANDARD),
                Chapter("[গল্প] ৪. বিলাসী - শরৎচন্দ্র চট্টোপাধ্যায়", TaskSetType.STANDARD),
                Chapter("[গল্প] ৫. অর্ধাঙ্গী - রোকেয়া সাখাওয়াত হোসেন", TaskSetType.STANDARD),
                Chapter("[গল্প] ৬. যৌবনের গান - কাজী নজরুল ইসলাম", TaskSetType.STANDARD),
                Chapter("[গল্প] ৭. জীবন ও বৃক্ষ - মোতাহের হোসেন চৌধুরী", TaskSetType.STANDARD),
                Chapter("[গল্প] ৮. গন্তব্য কাবুল - সৈয়দ মুজতবা আলী", TaskSetType.STANDARD),
                Chapter("[গল্প] ৯. মাসি-পিসি - মানিক বন্দ্যোপাধ্যায়", TaskSetType.STANDARD),
                Chapter("[গল্প] ১০. কিপলদাস মুর্মুর শেষ কাজ - শওকত আলী", TaskSetType.STANDARD),
                Chapter("[গল্প] ১১. রেইনকোট - আখতারুজ্জামান ইলিয়াস", TaskSetType.STANDARD),
                Chapter("[গল্প] ১২. নেকলেস - গ্য দ্য মোপাসাঁ", TaskSetType.STANDARD, "https://drive.google.com/file/d/1mhzuerrXOxbiMYLwKOvqrS5BVedaSpQX/view?usp=drivesdk"),
                Chapter("[কবিতা] ১. ঋতু-বর্ণন - আলাওল", TaskSetType.STANDARD),
                Chapter("[কবিতা] ২. বিভীষণের প্রতি মেঘনাদ - মাইকেল মধুসূদন দত্ত", TaskSetType.STANDARD),
                Chapter("[কবিতা] ৩. সোনার তরী - রবীন্দ্রনাথ ঠাকুর", TaskSetType.STANDARD),
                Chapter("[কবিতা] ৪. বিদ্রোহী - কাজী নজরুল ইসলাম", TaskSetType.STANDARD),
                Chapter("[কবিতা] ৫. সুচেতনা - জীবনানন্দ দাশ", TaskSetType.STANDARD),
                Chapter("[কবিতা] ৬. প্রতিদান - জসীমউদ্দীন", TaskSetType.STANDARD),
                Chapter("[কবিতা] ৭. তাহারেই পড়ে মনে - সুফিয়া কামাল", TaskSetType.STANDARD),
                Chapter("[কবিতা] ৮. পদ্মা - ফররুখ আহমদ", TaskSetType.STANDARD),
                Chapter("[কবিতা] ৯. ফেব্রুয়ারি ১৯৬৯ - শামসুর রাহমান", TaskSetType.STANDARD),
                Chapter("[কবিতা] ১০. আঠারো বছর বয়স - সুকান্ত ভট্টাচার্য", TaskSetType.STANDARD, "https://drive.google.com/file/d/1XF2oDSoKi0Ox_Z1PWpeGLhmpOxqeNY_G/view"),
                Chapter("[কবিতা] ১১. আমি কিংবদন্তির কথা বলছি - আবু জাফর ওবায়দুল্লাহ", TaskSetType.STANDARD),
                Chapter("[কবিতা] ১২. প্রত্যাবর্তনের লজ্জা - আল মাহমুদ", TaskSetType.STANDARD)
            )
        ),
        Subject(
            name = "Bangla 2nd Paper",
            category = "EBI",
            colorStartHex = 0xFFEC4899,
            colorEndHex = 0xFFF43F5E,
            iconEmoji = "✍️",
            chapters = listOf(
                Chapter("[ব্যাকরণ] ১. বাংলা উচ্চারণের নিয়ম (৫ নম্বর)", TaskSetType.BANGLA_GRAMMAR),
                Chapter("[ব্যাকরণ] ২. বাংলা বানানের নিয়ম (৫ নম্বর)", TaskSetType.BANGLA_GRAMMAR),
                Chapter("[ব্যাকরণ] ৩. বাংলা ভাষার ব্যাকরণিক শব্দশ্রেণি (৫ নম্বর)", TaskSetType.BANGLA_GRAMMAR),
                Chapter("[ব্যাকরণ] ৪. বাংলা শব্দগঠন (উপসর্গ, প্রত্যয় ও সমাস) (৫ নম্বর)", TaskSetType.BANGLA_GRAMMAR),
                Chapter("[ব্যাকরণ] ৫. বাক্যতত্ত্ব (৫ নম্বর)", TaskSetType.BANGLA_GRAMMAR),
                Chapter("[ব্যাকরণ] ৬. বাংলা ভাষার অপপ্রয়োগ ও শুদ্ধ প্রয়োগ (৫ নম্বর)", TaskSetType.BANGLA_GRAMMAR),
                Chapter("[নির্মিতি] ১. পারিভাষিক শব্দ ও অনুবাদ (১০ নম্বর)", TaskSetType.SIMPLE_READING_WRITING),
                Chapter("[নির্মিতি] ২. দিনলিপি / ভাষণ ও প্রতিবেদন (১০ নম্বর)", TaskSetType.SIMPLE_READING_WRITING),
                Chapter("[নির্মিতি] ৩. ই-মেইল / পত্রলিখন / আবেদনপত্র (১০ নম্বর)", TaskSetType.SIMPLE_READING_WRITING),
                Chapter("[নির্মিতি] ৪. সারাংশ / সারমর্ম / ভাবসম্প্রসারণ (১০ নম্বর)", TaskSetType.SIMPLE_READING_WRITING),
                Chapter("[নির্মিতি] ৫. সংলাপ রচনা / ক্ষুদে গল্প রচনা (১০ নম্বর)", TaskSetType.SIMPLE_READING_WRITING),
                Chapter("[নির্মিতি] ৬. প্রবন্ধ-নিবন্ধ রচনা (২০ নম্বর)", TaskSetType.SIMPLE_READING_WRITING)
            )
        ),
        Subject(
            name = "English 1st Paper",
            category = "EBI",
            colorStartHex = 0xFF3B82F6,
            colorEndHex = 0xFF2563EB,
            iconEmoji = "📘",
            chapters = listOf(
                Chapter("Unit 1: People or Personalities", TaskSetType.EFT_UNIT_PASSAGE),
                Chapter("Unit 2: Dreams", TaskSetType.EFT_UNIT_PASSAGE),
                Chapter("Unit 3: Lifestyle", TaskSetType.EFT_UNIT_PASSAGE),
                Chapter("Unit 4: Youthful Encounters", TaskSetType.EFT_UNIT_PASSAGE),
                Chapter("Unit 5: Relationships", TaskSetType.EFT_UNIT_PASSAGE),
                Chapter("Unit 6: Adolescence", TaskSetType.EFT_UNIT_PASSAGE),
                Chapter("Unit 7: Human Rights", TaskSetType.EFT_UNIT_PASSAGE),
                Chapter("Unit 8: Peace and Conflict", TaskSetType.EFT_UNIT_PASSAGE),
                Chapter("Unit 9: Tours and Travels", TaskSetType.EFT_UNIT_PASSAGE),
                Chapter("Writing Part: Paragraph, Story, Graph & Informal Letter", TaskSetType.SIMPLE_READING_WRITING)
            )
        ),
        Subject(
            name = "English 2nd Paper",
            category = "EBI",
            colorStartHex = 0xFF6366F1,
            colorEndHex = 0xFF4F46E5,
            iconEmoji = "📝",
            chapters = listOf(
                Chapter("1. Gap filling with Prepositions (5 Marks)", TaskSetType.ENGLISH_GRAMMAR),
                Chapter("2. Special Clues (was born, have to, let alone, would rather...) (5 Marks)", TaskSetType.ENGLISH_GRAMMAR),
                Chapter("3. Completing Sentences (with clauses/phrases) (5 Marks)", TaskSetType.ENGLISH_GRAMMAR),
                Chapter("4. Right Form of Verbs (5 Marks)", TaskSetType.ENGLISH_GRAMMAR),
                Chapter("5. Narrative Style / Speech (5 Marks)", TaskSetType.ENGLISH_GRAMMAR),
                Chapter("6. Use of Modifiers (5 Marks)", TaskSetType.ENGLISH_GRAMMAR),
                Chapter("7. Sentence Connectors (5 Marks)", TaskSetType.ENGLISH_GRAMMAR),
                Chapter("8. Synonym and Antonym (5 Marks)", TaskSetType.ENGLISH_GRAMMAR),
                Chapter("9. Punctuation (5 Marks)", TaskSetType.ENGLISH_GRAMMAR),
                Chapter("Composition: Formal Letters & Paragraph Writing (40 Marks)", TaskSetType.SIMPLE_READING_WRITING)
            )
        ),
        Subject(
            name = "ICT (তথ্য ও যোগাযোগ প্রযুক্তি)",
            category = "EBI",
            colorStartHex = 0xFF10B981,
            colorEndHex = 0xFF059669,
            iconEmoji = "💻",
            chapters = listOf(
                Chapter("অধ্যায় ১: তথ্য ও যোগাযোগ প্রযুক্তি: বিশ্ব ও বাংলাদেশ প্রেক্ষিত", TaskSetType.STANDARD),
                Chapter("অধ্যায় ২: কমিউনিকেশন সিস্টেমস ও নেটওয়ার্কিং", TaskSetType.STANDARD),
                Chapter("অধ্যায় ৩: সংখ্যা পদ্ধতি ও ডিজিটাল ডিভাইস", TaskSetType.STANDARD),
                Chapter("অধ্যায় ৪: ওয়েব ডিজাইন পরিচিতি এবং HTML", TaskSetType.STANDARD),
                Chapter("অধ্যায় ৫: প্রোগ্রামিং ভাষা (C Programming)", TaskSetType.STANDARD),
                Chapter("অধ্যায় ৬: ডেটাবেজ ম্যানেজমেন্ট সিস্টেম (DBMS)", TaskSetType.STANDARD)
            )
        )
    )

    val hscPcmbSubjects = listOf(
        Subject("Physics 1st Paper", "PCMB", 0xFF0EA5E9, 0xFF0284C7, "⚡", physics1Chapters),
        Subject("Physics 2nd Paper", "PCMB", 0xFF0284C7, 0xFF0369A1, "🧲", physics2Chapters),
        Subject("Chemistry 1st Paper", "PCMB", 0xFF10B981, 0xFF059669, "🧪", chemistry1Chapters),
        Subject("Chemistry 2nd Paper", "PCMB", 0xFF059669, 0xFF047857, "🔬", chemistry2Chapters),
        Subject("Higher Math 1st Paper", "PCMB", 0xFFF59E0B, 0xFFD97706, "📐", higherMath1Chapters),
        Subject("Higher Math 2nd Paper", "PCMB", 0xFFD97706, 0xFFB45309, "📊", higherMath2Chapters),
        Subject("Biology 1st Paper (Botany)", "PCMB", 0xFF84CC16, 0xFF65A30D, "🌿", botanyChapters),
        Subject("Biology 2nd Paper (Zoology)", "PCMB", 0xFF14B8A6, 0xFF0D9488, "🧬", zoologyChapters)
    )

    // --- 2. VARSITY 'A' UNIT MODULES (FULL RESEARCH - ALL CHAPTERS) ---
    val varsityPhysicsMathSubjects = listOf(
        Subject(
            name = "Varsity Physics (1st Paper - All 10 Chapters)",
            category = "Varsity A",
            colorStartHex = 0xFF0284C7,
            colorEndHex = 0xFF0369A1,
            iconEmoji = "⚡",
            chapters = physics1Chapters.map { Chapter("Varsity: " + it.title, TaskSetType.VARSITY_STANDARD) }
        ),
        Subject(
            name = "Varsity Physics (2nd Paper - All 11 Chapters)",
            category = "Varsity A",
            colorStartHex = 0xFF0369A1,
            colorEndHex = 0xFF1E3A8A,
            iconEmoji = "🧲",
            chapters = physics2Chapters.map { Chapter("Varsity: " + it.title, TaskSetType.VARSITY_STANDARD) }
        ),
        Subject(
            name = "Varsity Higher Math (1st Paper - All 10 Chapters)",
            category = "Varsity A",
            colorStartHex = 0xFFF59E0B,
            colorEndHex = 0xFFD97706,
            iconEmoji = "📐",
            chapters = higherMath1Chapters.map { Chapter("Varsity: " + it.title, TaskSetType.VARSITY_STANDARD) }
        ),
        Subject(
            name = "Varsity Higher Math (2nd Paper - All 10 Chapters)",
            category = "Varsity A",
            colorStartHex = 0xFFD97706,
            colorEndHex = 0xFFB45309,
            iconEmoji = "📊",
            chapters = higherMath2Chapters.map { Chapter("Varsity: " + it.title, TaskSetType.VARSITY_STANDARD) }
        )
    )

    val varsityChemBioElectiveSubjects = listOf(
        Subject(
            name = "Varsity Chemistry (1st Paper - All 5 Chapters)",
            category = "Varsity A",
            colorStartHex = 0xFF10B981,
            colorEndHex = 0xFF059669,
            iconEmoji = "🧪",
            chapters = chemistry1Chapters.map { Chapter("Varsity: " + it.title, TaskSetType.VARSITY_STANDARD) }
        ),
        Subject(
            name = "Varsity Chemistry (2nd Paper - All 5 Chapters)",
            category = "Varsity A",
            colorStartHex = 0xFF059669,
            colorEndHex = 0xFF047857,
            iconEmoji = "🔬",
            chapters = chemistry2Chapters.map { Chapter("Varsity: " + it.title, TaskSetType.VARSITY_STANDARD) }
        ),
        Subject(
            name = "Varsity Biology (Botany - All 12 Chapters)",
            category = "Varsity A",
            colorStartHex = 0xFF84CC16,
            colorEndHex = 0xFF65A30D,
            iconEmoji = "🌿",
            chapters = botanyChapters.map { Chapter("Varsity: " + it.title, TaskSetType.VARSITY_STANDARD) }
        ),
        Subject(
            name = "Varsity Biology (Zoology - All 12 Chapters)",
            category = "Varsity A",
            colorStartHex = 0xFF14B8A6,
            colorEndHex = 0xFF0D9488,
            iconEmoji = "🧬",
            chapters = zoologyChapters.map { Chapter("Varsity: " + it.title, TaskSetType.VARSITY_STANDARD) }
        ),
        Subject(
            name = "Varsity Elective English (DU Ka Unit)",
            category = "Elective",
            colorStartHex = 0xFF6366F1,
            colorEndHex = 0xFF4F46E5,
            iconEmoji = "🅰️",
            chapters = listOf(
                Chapter("1. Parts of Speech Identification & Noun-Verb Forms", TaskSetType.VARSITY_STANDARD),
                Chapter("2. Subject-Verb Agreement & Right Forms of Verbs", TaskSetType.VARSITY_STANDARD),
                Chapter("3. Prepositions & Group Verbs Mastery", TaskSetType.VARSITY_STANDARD),
                Chapter("4. Vocabulary, Synonyms & Antonyms (High Frequency)", TaskSetType.VARSITY_STANDARD),
                Chapter("5. Idioms, Phrases & Pinpoint Error Detection", TaskSetType.VARSITY_STANDARD),
                Chapter("6. Reading Comprehension & Passage Solving Drill", TaskSetType.VARSITY_STANDARD),
                Chapter("7. DU Ka Unit Past 20 Years English Question Bank", TaskSetType.VARSITY_STANDARD)
            )
        ),
        Subject(
            name = "Varsity Elective Bangla (DU Ka Unit)",
            category = "Elective",
            colorStartHex = 0xFFEC4899,
            colorEndHex = 0xFFBE185D,
            iconEmoji = "🇧🇩",
            chapters = listOf(
                Chapter("১. ধ্বনিতত্ত্ব, ণ-ত্ব ও ষ-ত্ব বিধান", TaskSetType.VARSITY_STANDARD),
                Chapter("২. সন্ধি, সমাস ও প্রত্যয় নির্ণয় টেকনিক", TaskSetType.VARSITY_STANDARD),
                Chapter("৩. বানান ও বাক্য শুদ্ধিকরণ", TaskSetType.VARSITY_STANDARD),
                Chapter("৪. শব্দ সম্ভার, পারিভাষিক শব্দ ও বাগধারা", TaskSetType.VARSITY_STANDARD),
                Chapter("৫. বাংলা সাহিত্য ও ইতিহাস (প্রাচীন, মধ্য ও আধুনিক যুগ)", TaskSetType.VARSITY_STANDARD),
                Chapter("৬. বিগত ২০ বছরের ডিইউ বাংলা প্রশ্নব্যাংক সমাধান", TaskSetType.VARSITY_STANDARD)
            )
        ),
        Subject(
            name = "Varsity Elective General Knowledge (GST, JU, RU, CU & BUP)",
            category = "Elective",
            colorStartHex = 0xFFF43F5E,
            colorEndHex = 0xFFE11D48,
            iconEmoji = "🏛️",
            chapters = listOf(
                Chapter("১. প্রাচীন বাংলার ইতিহাস ও জনপদ (গৌড়, বঙ্গ, পুণ্ড্র, সমতট, হরিকেল)", TaskSetType.VARSITY_STANDARD),
                Chapter("২. বাংলায় মুসলিম শাসন, সুলতানি আমল ও বারো ভূঁইয়া", TaskSetType.VARSITY_STANDARD),
                Chapter("৩. পলাশীর যুদ্ধ, ব্রিটিশ শাসন ও ১৯৪৭ সালের দেশভাগ", TaskSetType.VARSITY_STANDARD),
                Chapter("৪. ভাষা আন্দোলন (১৯৫২), যুক্তফ্রন্ট (১৯৫৪) ও ৬ দফা আন্দোলন (১৯৬৬)", TaskSetType.VARSITY_STANDARD),
                Chapter("৫. আগরতলা ষড়যন্ত্র মামলা, ১৯৬৯-এর গণঅভ্যুত্থান ও ১৯৭০-এর নির্বাচন", TaskSetType.VARSITY_STANDARD),
                Chapter("৬. ১৯৭১ সালের মুক্তিযুদ্ধ: ৭ই মার্চের ভাষণ, গণহত্যা ও মুজিবনগর সরকার", TaskSetType.VARSITY_STANDARD),
                Chapter("৭. মুক্তিযুদ্ধের ১১টি সেক্টর, ৩টি ব্রিগেড ফোর্স ও নৌ-অভিযান জ্যাকপট", TaskSetType.VARSITY_STANDARD),
                Chapter("৮. মুক্তিযুদ্ধে সাতজন বীরশ্রেষ্ঠ ও খেতাবপ্রাপ্ত মুক্তিযোদ্ধাগণ", TaskSetType.VARSITY_STANDARD),
                Chapter("৯. জাতির পিতা বঙ্গবন্ধু শেখ মুজিবুর রহমান (জীবন, কর্ম ও রচিত গ্রন্থাবলী)", TaskSetType.VARSITY_STANDARD),
                Chapter("১০. স্বাধীন বাংলাদেশের সংবিধান (১৯৭২), জাতীয় প্রতীক ও বিখ্যাত ভাস্কর্য", TaskSetType.VARSITY_STANDARD),
                Chapter("১১. বাংলাদেশের ভৌগোলিক অবস্থান, সীমানা, নদ-নদী, পাহাড় ও সমুদ্রসীমা", TaskSetType.VARSITY_STANDARD),
                Chapter("১২. বাংলাদেশের উপজাতি ও ক্ষুদ্র নৃগোষ্ঠী এবং তাদের সাংস্কৃতিক উৎসব", TaskSetType.VARSITY_STANDARD),
                Chapter("১৩. বাংলাদেশের কৃষি, প্রাকৃতিক গ্যাস, কয়লা ও খনিজ সম্পদ", TaskSetType.VARSITY_STANDARD),
                Chapter("১৪. মেগা প্রজেক্ট: পদ্মা সেতু, মেট্রোরেল, কর্ণফুলী টানেল ও রূপপুর পারমাণবিক", TaskSetType.VARSITY_STANDARD),
                Chapter("১৫. প্রাচীন প্রত্নতাত্ত্বিক নিদর্শন ও ঐতিহাসিক দর্শনীয় স্থানসমূহ", TaskSetType.VARSITY_STANDARD),
                Chapter("১৬. বাংলাদেশের অর্থনীতি, ব্যাংক ব্যবস্থা, বাজেট ও সাম্প্রতিক জনশুমারি", TaskSetType.VARSITY_STANDARD),
                Chapter("১৭. আন্তর্জাতিক সংস্থা: জাতিসংঘ (UN) ও এর বিশেষায়িত সংস্থাসমূহ", TaskSetType.VARSITY_STANDARD),
                Chapter("১৮. আঞ্চলিক ও বৈশ্বিক জোট (SAARC, ASEAN, OIC, NATO, BRICS, EU)", TaskSetType.VARSITY_STANDARD),
                Chapter("১৯. বিশ্বের ভৌগোলিক পরিচিতি, বিখ্যাত প্রণালী, চ্যানেল ও সীমারেখা", TaskSetType.VARSITY_STANDARD),
                Chapter("২০. বিশ্বের বিভিন্ন দেশের রাজধানী, মুদ্রা, সংসদ ও বিখ্যাত ব্যক্তিত্ব", TaskSetType.VARSITY_STANDARD),
                Chapter("২১. আন্তর্জাতিক পরিবেশ, জলবায়ু সম্মেলন (COP) ও বিখ্যাত বৈশ্বিক চুক্তি", TaskSetType.VARSITY_STANDARD),
                Chapter("২২. বিজ্ঞান, মহাকাশ গবেষণা ও নোবেল পুরস্কার বিজয়ী আবিষ্কার", TaskSetType.VARSITY_STANDARD),
                Chapter("২৩. বিগত ২০ বছরের বিশ্ববিদ্যালয় ভর্তি পরীক্ষার জিকে প্রশ্নব্যাংক সমাধান", TaskSetType.VARSITY_STANDARD)
            )
        )
    )

    // --- 3. ENGINEERING TRACK MODULES (BUET, CKREUT, BUTEX, MIST) ---
    val engineeringSubjects = listOf(
        Subject(
            name = "Engineering Higher Math (1st Paper - All 10 Chapters)",
            category = "Engineering",
            colorStartHex = 0xFFD97706,
            colorEndHex = 0xFFB45309,
            iconEmoji = "📐",
            chapters = higherMath1Chapters.map { Chapter("BUET/CKREUT: " + it.title, TaskSetType.ENGINEERING_STANDARD) }
        ),
        Subject(
            name = "Engineering Higher Math (2nd Paper - All 10 Chapters)",
            category = "Engineering",
            colorStartHex = 0xFFB45309,
            colorEndHex = 0xFF78350F,
            iconEmoji = "📊",
            chapters = higherMath2Chapters.map { Chapter("BUET/CKREUT: " + it.title, TaskSetType.ENGINEERING_STANDARD) }
        ),
        Subject(
            name = "Engineering Physics (1st Paper - All 10 Chapters)",
            category = "Engineering",
            colorStartHex = 0xFF0284C7,
            colorEndHex = 0xFF0369A1,
            iconEmoji = "⚡",
            chapters = physics1Chapters.map { Chapter("BUET/CKREUT: " + it.title, TaskSetType.ENGINEERING_STANDARD) }
        ),
        Subject(
            name = "Engineering Physics (2nd Paper - All 11 Chapters)",
            category = "Engineering",
            colorStartHex = 0xFF0369A1,
            colorEndHex = 0xFF1E3A8A,
            iconEmoji = "🧲",
            chapters = physics2Chapters.map { Chapter("BUET/CKREUT: " + it.title, TaskSetType.ENGINEERING_STANDARD) }
        ),
        Subject(
            name = "Engineering Chemistry (1st Paper - All 5 Chapters)",
            category = "Engineering",
            colorStartHex = 0xFF10B981,
            colorEndHex = 0xFF059669,
            iconEmoji = "🧪",
            chapters = chemistry1Chapters.map { Chapter("BUET/CKREUT: " + it.title, TaskSetType.ENGINEERING_STANDARD) }
        ),
        Subject(
            name = "Engineering Chemistry (2nd Paper - All 5 Chapters)",
            category = "Engineering",
            colorStartHex = 0xFF059669,
            colorEndHex = 0xFF047857,
            iconEmoji = "🔬",
            chapters = chemistry2Chapters.map { Chapter("BUET/CKREUT: " + it.title, TaskSetType.ENGINEERING_STANDARD) }
        )
    )

    // --- 4. MEDICAL ADMISSION TRACK MODULES (DGHS / MATS 100 MARKS PATTERN) ---
    val medicalBioSubjects = listOf(
        Subject(
            name = "Medical Biology - Botany (All 12 Chapters)",
            category = "Medical 30M",
            colorStartHex = 0xFF84CC16,
            colorEndHex = 0xFF65A30D,
            iconEmoji = "🌿",
            chapters = botanyChapters.map { Chapter("Medical: আবুল হাসান স্যার - " + it.title, TaskSetType.MEDICAL_STANDARD) }
        ),
        Subject(
            name = "Medical Biology - Zoology (All 12 Chapters)",
            category = "Medical 30M",
            colorStartHex = 0xFF14B8A6,
            colorEndHex = 0xFF0D9488,
            iconEmoji = "🧬",
            chapters = zoologyChapters.map { Chapter("Medical: গাজী আজমল স্যার - " + it.title, TaskSetType.MEDICAL_STANDARD) }
        )
    )

    val medicalChemPhysicsSubjects = listOf(
        Subject(
            name = "Medical Chemistry (1st Paper - All 5 Chapters)",
            category = "Medical 25M",
            colorStartHex = 0xFF10B981,
            colorEndHex = 0xFF059669,
            iconEmoji = "🧪",
            chapters = chemistry1Chapters.map { Chapter("Medical: হাজারী-নাগ - " + it.title, TaskSetType.MEDICAL_STANDARD) }
        ),
        Subject(
            name = "Medical Chemistry (2nd Paper - All 5 Chapters)",
            category = "Medical 25M",
            colorStartHex = 0xFF059669,
            colorEndHex = 0xFF047857,
            iconEmoji = "🔬",
            chapters = chemistry2Chapters.map { Chapter("Medical: কবির/হাজারী - " + it.title, TaskSetType.MEDICAL_STANDARD) }
        ),
        Subject(
            name = "Medical Physics (1st Paper - All 10 Chapters)",
            category = "Medical 20M",
            colorStartHex = 0xFF0284C7,
            colorEndHex = 0xFF0369A1,
            iconEmoji = "⚡",
            chapters = physics1Chapters.map { Chapter("Medical: ইসহাক স্যার - " + it.title, TaskSetType.MEDICAL_STANDARD) }
        ),
        Subject(
            name = "Medical Physics (2nd Paper - All 11 Chapters)",
            category = "Medical 20M",
            colorStartHex = 0xFF0369A1,
            colorEndHex = 0xFF1E3A8A,
            iconEmoji = "🧲",
            chapters = physics2Chapters.map { Chapter("Medical: তোপন/ইসহাক - " + it.title, TaskSetType.MEDICAL_STANDARD) }
        )
    )

    val medicalEnglishGkSubjects = listOf(
        Subject(
            name = "Medical English (15 Marks)",
            category = "Medical 15M",
            colorStartHex = 0xFF6366F1,
            colorEndHex = 0xFF4F46E5,
            iconEmoji = "🔤",
            chapters = listOf(
                Chapter("1. Parts of Speech Identification & Uses", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("2. Subject-Verb Agreement & Conditionals", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("3. Appropriate Prepositions & Group Verbs", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("4. Synonyms, Antonyms & Medical Terminology", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("5. Correction & Sentence Structure", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("6. Voice, Narration & Modifiers", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("7. Idioms, Phrases & Proverbs", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("8. Medical Past 20 Years English Question Bank", TaskSetType.MEDICAL_GK_ENGLISH)
            )
        ),
        Subject(
            name = "Medical GK: বাংলাদেশ বিষয়াবলী (ইতিহাস, মুক্তিযুদ্ধ ও বঙ্গবন্ধু)",
            category = "Medical GK",
            colorStartHex = 0xFFF43F5E,
            colorEndHex = 0xFFE11D48,
            iconEmoji = "🏛️",
            chapters = listOf(
                Chapter("১. প্রাচীন বাংলার ইতিহাস ও বিভিন্ন জনপদ (মৌর্য, গুপ্ত, পাল ও সেন আমল)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("২. বাংলায় মুসলিম শাসন ও সুলতানি আমল (ইলিয়াস শাহী ও হোসেন শাহী যুগ)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৩. মোঘল আমল ও বারো ভূঁইয়া (সম্রাট বাবর, আকবর, জাহাঙ্গীর ও সুবেদার ইসলাম খান)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৪. বাংলায় ইউরোপীয়দের আগমন ও ব্রিটিশ শাসন প্রতিষ্ঠা (পলাশী ও বক্সারের যুদ্ধ)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৫. ব্রিটিশ বিরোধী স্বাধীনতা সংগ্রাম, ফরায়েজী আন্দোলন ও সিপাহী বিদ্রোহ (১৮৫৭)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৬. বঙ্গভঙ্গ (১৯০৫), বঙ্গভঙ্গ রদ (১৯১১) ও ১৯৪৭ সালের দেশভাগ", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৭. ভাষা আন্দোলন (১৯৪৮-১৯৫২), তমুদ্দুন মজলিস ও ২১শে ফেব্রুয়ারি", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৮. যুক্তফ্রন্ট নির্বাচন (১৯৫৪), ২১ দফা ও ১৯৫৮ সালের সামরিক শাসন", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৯. ১৯৬২ সালের ঐতিহাসিক শিক্ষা আন্দোলন ও ছাত্রসমাজ", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("১০. ঐতিহাসিক ছয় দফা দাবি ১৯৬৬ (বাঙালির মুক্তির সনদ ও লাহোর প্রস্তাব)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("১১. আগরতলা ষড়যন্ত্র মামলা (১৯৬৮) ও ১৯৬৯ সালের ঐতিহাসিক গণঅভ্যুত্থান", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("১২. ১৯৭০ সালের সাধারণ নির্বাচন ও আওয়ামী লীগের নিরঙ্কুশ বিজয়", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("১৩. ১৯৭১ সালের অগ্নিঝরা মার্চ ও বঙ্গবন্ধুর ঐতিহাসিক ৭ই মার্চের ভাষণ (UNESCO স্বীকৃতি)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("১৪. ২৫শে মার্চের কালরাত ও পাকিস্তানি হানাদার বাহিনীর 'অপারেশন সার্চলাইট'", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("১৫. ২৬শে মার্চ প্রথম প্রহরে বঙ্গবন্ধুর স্বাধীনতার আনুষ্ঠানিক ঘোষণা ও প্রতিরোধ যুদ্ধ", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("১৬. ১০ই এপ্রিল স্বাধীনতার ঘোষণাপত্র ও ১৭ই এপ্রিল মুজিবনগর সরকার গঠন ও শপথ", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("১৭. মুক্তিযুদ্ধের ১১টি সেক্টর, সেক্টর কমান্ডারগণ ও সাব-সেক্টরসমূহ", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("১৮. মুক্তিযুদ্ধের ৩টি নিয়মিত ব্রিগেড ফোর্স (জেড ফোর্স, কে ফোর্স, এস ফোর্স)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("১৯. মুক্তিযুদ্ধে নৌ-কমান্ডো ও দুঃসাহসিক অভিযান 'অপারেশন জ্যাকপট' ও ক্র্যাক প্লাটুন", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("২০. মুক্তিযুদ্ধে সাতজন বীরশ্রেষ্ঠ (নাম, বাহিনী, সেক্টর, সমাধিস্থল ও শহিদ দিবস)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("২১. মুক্তিযুদ্ধে খেতাবপ্রাপ্ত বীর মুক্তিযোদ্ধা (বীরউত্তম, বীরবিক্রম, বীরপ্রতীক ও বীরঙ্গনা)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("২২. স্বাধীন বাংলা বেতার কেন্দ্র, মুক্তিযুদ্ধের গান ও বিদেশি বন্ধুদের অবদান", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("২৩. ১৪ই ডিসেম্বর শহীদ বুদ্ধিজীবী হত্যাকাণ্ড ও ১৬ই ডিসেম্বর ঐতিহাসিক বিজয়", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("২৪. জাতির পিতা বঙ্গবন্ধু শেখ মুজিবুর রহমান (শৈশব, সংগ্রাম, ৭ই মার্চ ও অবদান)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("২৫. বঙ্গবন্ধুর রচিত গ্রন্থাবলী (অসমাপ্ত আত্মজীবনী, কারাগারের রোজনামচা, আমার দেখা নয়াচীন)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("২৬. ১৯৭৫ সালের ১৫ই আগস্ট জাতীয় শোক দিবস ও বর্বরোচিত হত্যাকাণ্ড", TaskSetType.MEDICAL_GK_ENGLISH)
            )
        ),
        Subject(
            name = "Medical GK: বাংলাদেশ বিষয়াবলী (সংবিধান, ভূগোল, সম্পদ ও মেগা প্রজেক্ট)",
            category = "Medical GK",
            colorStartHex = 0xFF059669,
            colorEndHex = 0xFF047857,
            iconEmoji = "🇧🇩",
            chapters = listOf(
                Chapter("১. স্বাধীন বাংলাদেশের সংবিধান (১৯৭২): মূল চার নীতি, গুরুত্বপূর্ণ অনুচ্ছেদ ও সংশোধনী", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("২. বাংলাদেশের জাতীয় প্রতীকসমূহ (পতাকা, জাতীয় সংগীত, প্রতীক ও মনোগ্রাম)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৩. জাতীয় স্মৃতিসৌধ, শহীদ মিনার, অপরাজেয় বাংলা ও দেশের বিখ্যাত ভাস্কর্য-স্থপতি", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৪. বাংলাদেশের ভৌগোলিক অবস্থান, আয়তন, সীমানা, ছিটমহল ও সমুদ্রসীমা বিজয়", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৫. বাংলাদেশের প্রধান নদ-নদী (উৎপত্তি, মোহনা, মিলনস্থল), বিল, হাওড় ও জলপ্রপাত", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৬. বাংলাদেশের পাহাড়-পর্বত (কেওক্রাডং, সাকাহাফং), বনাঞ্চল ও সুন্দরবন", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৭. বাংলাদেশের আবহাওয়া, জলবায়ু, ষড়ঋতু ও প্রধান প্রাকৃতিক দুর্যোগসমূহ", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৮. বাংলাদেশের ক্ষুদ্র নৃগোষ্ঠী ও উপজাতি (চাকমা, মারমা, গারো, সাঁওতাল, খাসিয়া) ও বৈসাবি", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৯. বাংলাদেশের কৃষি, মৎস্য ও খনিজ সম্পদ (প্রাকৃতিক গ্যাসক্ষেত্র, কয়লাখনি ও চুনাপাথর)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("১০. মেগা প্রজেক্ট: পদ্মা সেতু, মেট্রোরেল (MRT) ও বঙ্গবন্ধু কর্ণফুলী টানেল", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("১১. রূপপুর পারমাণবিক বিদ্যুৎ কেন্দ্র, মাতারবাড়ি গভীর সমুদ্র বন্দর ও বিমানবন্দর ৩য় টার্মিনাল", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("১২. বাংলাদেশের প্রত্নতাত্ত্বিক নিদর্শন (মহাস্থানগড়, পাহাড়পুর বিহার, লালবাগ কেল্লা, ষাট গম্বুজ)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("১৩. বাংলাদেশের অর্থনীতি, ব্যাংক ব্যবস্থা, প্রধান রপ্তানি পণ্য, বাজেট ও সাম্প্রতিক জনশুমারি", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("১৪. বিখ্যাত বাঙালি মনীষী, বিজ্ঞানী, কবি-সাহিত্যিকদের জীবন ও উল্লেখযোগ্য সাহিত্যকর্ম", TaskSetType.MEDICAL_GK_ENGLISH)
            )
        ),
        Subject(
            name = "Medical GK: আন্তর্জাতিক বিষয়াবলী, স্বাস্থ্যবিজ্ঞান ও প্রশ্নব্যাংক",
            category = "Medical GK",
            colorStartHex = 0xFFEAB308,
            colorEndHex = 0xFFCA8A04,
            iconEmoji = "🌍",
            chapters = listOf(
                Chapter("১. আন্তর্জাতিক ভৌগোলিক পরিচিতি (মহাদেশ, মহাসাগর, প্রণালী, চ্যানেল ও সীমারেখা)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("২. আন্তর্জাতিক সংস্থা ও জোট (জাতিসংঘ, WHO, UNICEF, UNESCO, SAARC, ASEAN, NATO, BRICS)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৩. বিশ্বের প্রধান চুক্তি, সম্মেলন, জেনেভা কনভেনশন ও বিশ্ব জলবায়ু সম্মেলন (COP)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৪. বিশ্বের বিভিন্ন দেশের রাজধানী, মুদ্রা, সংসদ ও বিখ্যাত সীমারেখা", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৫. বিজ্ঞান, মহাকাশ গবেষণা (NASA, ISRO) ও আধুনিক প্রযুক্তি আবিষ্কার", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৬. স্বাস্থ্য ও চিকিৎসা বিজ্ঞানের ইতিহাস, বিখ্যাত আবিষ্কার ও নোবেল পুরস্কার বিজয়ীগণ", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৭. বিশ্ব স্বাস্থ্য সংস্থা (WHO) এর গুরুত্বপূর্ণ স্বাস্থ্য কর্মসূচি ও আন্তর্জাতিক স্বাস্থ্য দিবস", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৮. বিগত ২০ বছরের মেডিকেল ভর্তি পরীক্ষার জিকে প্রশ্নব্যাংক সমাধান (Medical GK Past 20 Years)", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("৯. ডেন্টাল (BDS) ও আর্মড ফোর্সেস মেডিকেল কলেজের বিগত বছরের প্রশ্ন সমাধান", TaskSetType.MEDICAL_GK_ENGLISH),
                Chapter("১০. বিসিএস ও বিশ্ববিদ্যালয় ভর্তি পরীক্ষার শীর্ষ কমনোপযোগী জিকে স্পেশাল মডেল টেস্ট", TaskSetType.MEDICAL_GK_ENGLISH)
            )
        )
    )

    // --- 5. VARSITY 'B' UNIT MODULES (ARTS, LAW & SOCIAL SCIENCE - FULL RESEARCH) ---
    private val varsityBBangla1Chapters = listOf(
        Chapter("১. গদ্য: অপরিচিতা ও বিলাসী (রবীন্দ্রনাথ ও শরৎচন্দ্র)", TaskSetType.BANGLA_GRAMMAR),
        Chapter("২. গদ্য: আমার পথ (কাজী নজরুল ইসলাম) ও মানব কল্যাণ (আবুল ফজল)", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৩. গদ্য: বায়ান্নর দিনগুলো (বঙ্গবন্ধু) ও রেইনকোট (আখতারুজ্জামান ইলিয়াস)", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৪. গদ্য: মহাজাগতিক কিউরেটর ও নেকলেস (গি দ্য মোপাসাঁ)", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৫. কবিতা: বিভীষণের প্রতি মেঘনাদ ও সোনার তরী", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৬. কবিতা: বিদ্রোহী (নজরুল) ও প্রতিদান (জসীমউদ্দীন)", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৭. কবিতা: সুচেতনা (জীবনানন্দ দাশ) ও তাহারেই পড়ে মনে", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৮. কবিতা: পদ্মা (ফররুখ আহমদ) ও আঠারো বছর বয়স (সুকান্ত)", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৯. কবিতা: ফেব্রুয়ারি ১৯৬৯ ও আমি কিংবদন্তির কথা বলছি", TaskSetType.BANGLA_GRAMMAR),
        Chapter("১০. সহপাঠ উপন্যাস: 'লালসালু' (সৈয়দ ওয়ালীউল্লাহ) মূলভাব ও চরিত্র", TaskSetType.BANGLA_GRAMMAR),
        Chapter("১১. সহপাঠ নাটক: 'সিরাজউদ্দৌলা' (সিকান্দার আবু জাফর) সংলাপ ও ইতিহাস", TaskSetType.BANGLA_GRAMMAR),
        Chapter("১২. DU B Unit ও GST B বিগত ২০ বছরের বাংলা ১ম প্রশ্নব্যাংক", TaskSetType.VARSITY_STANDARD)
    )

    private val varsityBBangla2Chapters = listOf(
        Chapter("১. বাংলা উচ্চারণের নিয়ম (অধ্বনি, এধ্বনি, ব-ফলা ও ম-ফলা)", TaskSetType.BANGLA_GRAMMAR),
        Chapter("২. বাংলা বানানের নিয়ম ও ন-ত্ব ও ষ-ত্ব বিধান", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৩. বাংলা ব্যাকরণিক শব্দশ্রেণি (বিশেষ্য, বিশেষণ, সর্বনাম, ক্রিয়া, অনুসর্গ)", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৪. শব্দ গঠন: উপসর্গ, অনুসর্গ ও কৃৎ-তদ্ধিত প্রত্যয়", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৫. সন্ধি নির্ণয় ও ব্যতিক্রমী নিয়মাবলী", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৬. সমাস নির্ণয় (দ্বন্দ্ব, কর্মধারয়, তৎপুরুষ, বহুব্রীহি, দ্বিগু, অব্যয়ীভাব)", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৭. বাক্য রূপান্তর (সরল, জটিল, যৌগিক) ও বাচ্য পরিবর্তন", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৮. শব্দার্থের বিস্তার: সমার্থক শব্দ, বিপরীত শব্দ ও এককথায় প্রকাশ", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৯. বাগধারা ও প্রবাদ-প্রবচন (High-Yield Varsity Collection)", TaskSetType.BANGLA_GRAMMAR),
        Chapter("১০. পারিভাষিক শব্দ (Academic & Official) ও অনুবাদ", TaskSetType.BANGLA_GRAMMAR),
        Chapter("১১. বাংলা সাহিত্যের ইতিহাস: প্রাচীন, মধ্যযুগ ও আধুনিক যুগ", TaskSetType.BANGLA_GRAMMAR),
        Chapter("১২. বিখ্যাত কবি-সাহিত্যিকদের উপাধি, বিখ্যাত গ্রন্থ ও পত্রিকা", TaskSetType.BANGLA_GRAMMAR)
    )

    private val varsityBEnglishGrammarChapters = listOf(
        Chapter("১. Parts of Speech Identification & Determiners", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("২. Subject-Verb Agreement & Inversion Rules", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৩. Tense, Sequence of Tense & Right Form of Verbs", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৪. Modals, Conditionals (0, 1st, 2nd, 3rd) & Subjunctive", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৫. Dangling Modifiers, Misplaced Modifiers & Parallelism", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৬. Voice Change & Direct-Indirect Narration Transformation", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৭. Prepositions & High-Yield Appropriate Prepositions", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৮. Articles, Quantifiers (Few, Little, Many, Much) & Causative Verbs", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৯. Clauses & Phrases Identification (Noun, Adjective, Adverbial)", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("১০. Pinpoint Error Spotting & Sentence Correction", TaskSetType.ENGLISH_GRAMMAR)
    )

    private val varsityBEnglishLitVocabChapters = listOf(
        Chapter("১. High-Yield Vocabulary (Barron's 333 + Varsity High Frequency)", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("২. Synonyms & Antonyms (Past 20 Years University QB)", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৩. Idioms, Phrases & Phrasal Verbs Application", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৪. One Word Substitution & Foreign Phrases", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৫. Verbal Analogy & Spelling Rules (Common Errors)", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৬. Reading Comprehension Strategy: Main Idea, Tone & Inference", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৭. English Literature Periods (Elizabethan, Romantic, Victorian, Modern)", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৮. Major English Poets, Dramatists, Works & Famous Quotes", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৯. Figures of Speech (Metaphor, Simile, Irony, Personification)", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("১০. DU B Unit Written English: Paragraph, Summary & Translation", TaskSetType.VARSITY_STANDARD)
    )

    private val varsityBBangladeshGkChapters = listOf(
        Chapter("১. প্রাচীন বাংলার ইতিহাস: জনপদ, মৌর্য, গুপ্ত ও সেন শাসন", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("২. মুসলিম শাসন, সুলতানি আমল, বারোভূঁইয়া ও মুঘল সুবাদারি আমল", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৩. ব্রিটিশ ঔপনিবেশিক শাসন, পলাশীর যুদ্ধ (১৭৫৭) ও প্রতিরোধ আন্দোলন", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৪. ১৯৪৭ দেশভাগ থেকে ১৯৫২ ভাষা আন্দোলন ও ২১শে ফেব্রুয়ারি", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৫. ১৯৫৪ যুক্তফ্রন্ট নির্বাচন, ২১ দফা ও ১৯৫৮ সামরিক শাসন", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৬. ১৯৬২ শিক্ষা আন্দোলন ও ছাত্রসমাজের ঐতিহাসিক ভূমিকা", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৭. ১৯৬৬ সালের ঐতিহাসিক ৬ দফা দাবি (বাঙালির মুক্তির সনদ)", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৮. ১৯৬৮ আগরতলা ষড়যন্ত্র মামলা ও ১৯৬৯ গণঅভ্যুত্থান", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৯. ১৯৭০ সাধারণ নির্বাচন, ৭ই মার্চের ঐতিহাসিক ভাষণ ও অসহযোগ আন্দোলন", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("১০. ১৯৭১ মুক্তিযুদ্ধ: অপারেশন সার্চলাইট, মুজিবনগর সরকার ও ১১টি সেক্টর", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("১১. সাত বীরশ্রেষ্ঠ, অপারেশন জ্যাকপট ও ১৬ই ডিসেম্বর ঐতিহাসিক বিজয়", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("১২. জাতির পিতা বঙ্গবন্ধু শেখ মুজিবুর রহমান (জীবন, গ্রন্থ ও অবদান)", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("১৩. গণপ্রজাতন্ত্রী বাংলাদেশের সংবিধান (১৯৭২): মূলনীতি, অনুচ্ছেদ ও সংশোধনী", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("১৪. সরকার ব্যবস্থা: নির্বাহী বিভাগ, জাতীয় সংসদ, বিচার বিভাগ ও নির্বাচন কমিশন", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("১৫. ভৌগোলিক অবস্থান, নদ-নদী, পাহাড়, দ্বীপ, ছিটমহল ও সমুদ্রসীমা বিজয়", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("১৬. বাংলাদেশের অর্থনীতি, বাজেট ও মেগা প্রজেক্ট (পদ্মা সেতু, MRT, টানেল)", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("১৭. ক্ষুদ্র নৃগোষ্ঠী, সংস্কৃতি, প্রাচীন প্রত্নতত্ত্ব ও দর্শনীয় স্থান", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("১৮. সাম্প্রতিক বাংলাদেশ ও আলোচিত জাতীয় ঘটনাবলী (Recent BD Affairs)", TaskSetType.MEDICAL_GK_ENGLISH)
    )

    private val varsityBIntlGkChapters = listOf(
        Chapter("১. ভৌগোলিক পরিচিতি: মহাদেশ, মহাসাগর, প্রণালী, খাল ও সীমারেখা", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("২. প্রথম ও দ্বিতীয় বিশ্বযুদ্ধ, ভার্সাই চুক্তি ও লিগ অব নেশনস", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৩. জাতিসংঘ (UN): মহাসচিব, নিরাপত্তা পরিষদ ও প্রধান অঙ্গসমূহ", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৪. আন্তর্জাতিক সংস্থা: UNESCO, UNICEF, WHO, ILO, UNHCR, FAO", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৫. বৈশ্বিক অর্থনীতি: World Bank, IMF, ADB, NDB, WTO, WEF", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৬. আঞ্চলিক জোট: SAARC, BIMSTEC, ASEAN, BRICS, SCO, OIC, NATO", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৭. পরিবেশ ও জলবায়ু সম্মেলন: কিয়োটো প্রটোকল, প্যারিস চুক্তি ও COP", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৮. ঐতিহাসিক বিপ্লব: ফরাসি বিপ্লব, রুশ বিপ্লব, শিল্প বিপ্লব ও আরব বসন্ত", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৯. বৈশ্বিক ভূরাজনীতি: ফিলিস্তিন সংকট, রাশিয়া-ইউক্রেন যুদ্ধ ও সমকালীন কূটনীতি", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("১০. গুরুত্বপূর্ণ দেশের রাজধানী, মুদ্রা, সংসদ ভবন ও জাতীয় প্রতীক", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("১১. নোবেল পুরস্কার, অস্কার, পুলিৎজার, বুকার ও ম্যাগসেসে পুরস্কার", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("১২. আন্তর্জাতিক খেলাধুলা: বিশ্বকাপ ফুটবল, ক্রিকেট ও অলিম্পিক গেমস", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("১৩. সমকালীন আন্তর্জাতিক ঘটনাবলী ও শীর্ষ পরিসংখ্যান (Recent Global Affairs)", TaskSetType.MEDICAL_GK_ENGLISH)
    )

    private val varsityBIctAnalyticalChapters = listOf(
        Chapter("১. কম্পিউটার পরিচিতি: ইনপুট-আউটপুট, CPU ও মেমোরি সিস্টেম", TaskSetType.VARSITY_STANDARD),
        Chapter("২. ইন্টারনেট প্রটোকল, সার্চ ইঞ্জিন, ইমেইল ও সোশ্যাল মিডিয়া", TaskSetType.VARSITY_STANDARD),
        Chapter("৩. সাইবার নিরাপত্তা, কম্পিউটার ভাইরাস ও তথ্যপ্রযুক্তি আইন", TaskSetType.VARSITY_STANDARD),
        Chapter("৪. কৃত্রিম বুদ্ধিমত্তা (AI), ক্লাউড কম্পিউটিং ও স্মার্ট প্রযুক্তি", TaskSetType.VARSITY_STANDARD),
        Chapter("৫. সাধারণ বিজ্ঞান: দৈনন্দিন বিজ্ঞান, রোগব্যাধি ও পুষ্টিবিজ্ঞান", TaskSetType.VARSITY_STANDARD),
        Chapter("৬. মানসিক দক্ষতা (Mental Ability): সংখ্যা ও বর্ণ সিরিজ, কোডিং", TaskSetType.VARSITY_STANDARD),
        Chapter("৭. লজিক্যাল রিজনিং: সম্পর্ক নির্ণয়, দিক নির্ণয় ও ঘড়ি-ক্যালেন্ডার", TaskSetType.VARSITY_STANDARD),
        Chapter("৮. DU B Unit ও GST B বিগত বছরের মডেল টেস্ট ও প্রশ্নব্যাংক", TaskSetType.VARSITY_STANDARD)
    )

    val varsityBSubjects = listOf(
        Subject("বাংলা ১ম পত্র (সাহিত্য ও টেক্সটবুক)", "Bangla", 0xFFEC4899, 0xFFBE185D, "📖", varsityBBangla1Chapters),
        Subject("বাংলা ২য় পত্র (ব্যাকরণ ও শব্দসম্ভার)", "Bangla", 0xFFBE185D, 0xFF831843, "✍️", varsityBBangla2Chapters),
        Subject("English Grammar & Sentence Analysis", "English", 0xFF6366F1, 0xFF4338CA, "🔤", varsityBEnglishGrammarChapters),
        Subject("English Literature, Vocab & Written", "English", 0xFF8B5CF6, 0xFF6D28D9, "📚", varsityBEnglishLitVocabChapters),
        Subject("বাংলাদেশ বিষয়াবলী (ইতিহাস, সংবিধান ও সম্পদ)", "General Knowledge", 0xFF10B981, 0xFF047857, "🇧🇩", varsityBBangladeshGkChapters),
        Subject("আন্তর্জাতিক বিষয়াবলী ও বিশ্বরাজনীতি", "General Knowledge", 0xFF0284C7, 0xFF0369A1, "🌍", varsityBIntlGkChapters),
        Subject("মৌলিক জিকে, আইসিটি ও মানসিক দক্ষতা", "ICT & Analytical", 0xFFF59E0B, 0xFFD97706, "💡", varsityBIctAnalyticalChapters)
    )

    // --- 6. VARSITY 'C' UNIT MODULES (BUSINESS STUDIES / COMMERCE - FULL RESEARCH) ---
    private val varsityCAccounting1Chapters = listOf(
        Chapter("১. হিসাববিজ্ঞান পরিচিতি ও হিসাববিজ্ঞানের নীতিমালা (GAAP ও IFRS)", TaskSetType.STANDARD),
        Chapter("২. হিসাব সমীকরণ (A = L + OE) ও লেনদেন বিশ্লেষণ", TaskSetType.STANDARD),
        Chapter("৩. হিসাবের বইসমূহ: সাধারণ জাবেদা, খতিয়ান ও টি-ছক", TaskSetType.STANDARD),
        Chapter("৪. বিশেষ জাবেদা: ক্রয়, বিক্রয়, নগদ প্রাপ্তি ও প্রদান জাবেদা", TaskSetType.STANDARD),
        Chapter("৫. রেওয়ামিল প্রস্তুত ও হিসাবের ভুল সংশোধনী দাখিলা", TaskSetType.STANDARD),
        Chapter("৬. ব্যাংক সমন্বয় বিবরণী (Bank Reconciliation Statement)", TaskSetType.STANDARD),
        Chapter("৭. কার্যপত্র ও সমন্বয় দাখিলা (Adjusting Entries & Work Sheet)", TaskSetType.STANDARD),
        Chapter("৮. দৃশ্যমান ও অদৃশ্যমান সম্পদের হিসাববিজ্ঞান (অবচয় নির্ণয়)", TaskSetType.STANDARD),
        Chapter("৯. একতরফা দাখিলা পদ্ধতি ও প্রারম্ভিক হিসাবরক্ষণ", TaskSetType.STANDARD),
        Chapter("১০. আর্থিক বিবরণী: বিশদ আয় বিবরণী ও ব্যালেন্স শিট", TaskSetType.STANDARD)
    )

    private val varsityCAccounting2Chapters = listOf(
        Chapter("১. অব্যবসায়ী প্রতিষ্ঠানের হিসাববিজ্ঞান (প্রাপ্তি ও প্রদান, আয়-ব্যয়)", TaskSetType.STANDARD),
        Chapter("২. অংশীদারি ব্যবসায়ের হিসাব (লাভ-লোকসান বণ্টন ও মূলধন হিসাব)", TaskSetType.STANDARD),
        Chapter("৩. অংশীদারি ব্যবসায়ের সুনাম মূল্যায়ন ও পুনর্মূল্যায়ন", TaskSetType.STANDARD),
        Chapter("৪. যৌথ মূলধনী কোম্পানি: শেয়ার মূলধন ও শেয়ার ইস্যু (অধিহার ও অবহার)", TaskSetType.STANDARD),
        Chapter("৫. যৌথ মূলধনী কোম্পানির আর্থিক বিবরণী প্রস্তুত ও বিশ্লেষণ", TaskSetType.STANDARD),
        Chapter("৬. আর্থিক বিবরণী বিশ্লেষণ ও গুরুত্বপূর্ণ অনুপাত (Liquidity, Solvency)", TaskSetType.STANDARD),
        Chapter("৭. উৎপাদন ব্যয় হিসাববিজ্ঞান (Cost Sheet & Break-Even Point)", TaskSetType.STANDARD),
        Chapter("৮. বেতন ও মজুরি সংক্রান্ত হিসাববিজ্ঞান (Gross & Net Pay)", TaskSetType.STANDARD),
        Chapter("৯. নগদ প্রবাহ বিবরণী (Cash Flow Statement)", TaskSetType.STANDARD),
        Chapter("১০. DU 'Ga' / 'C' Unit ও GST C বিগত ২০ বছরের হিসাববিজ্ঞান প্রশ্নব্যাংক", TaskSetType.STANDARD)
    )

    private val varsityCManagementChapters = listOf(
        Chapter("১. ব্যবসায়ের মৌলিক ধারণা, গুরুত্ব ও ব্যবসায় পরিবেশের উপাদান", TaskSetType.STANDARD),
        Chapter("২. একমালিকানা ও অংশীদারি ব্যবসায় (গঠন, সুবিধা ও বিলোপসাধন)", TaskSetType.STANDARD),
        Chapter("৩. যৌথ মূলধনী কোম্পানি: গঠন, স্মারকলিপি ও পরিমেল নিয়মাবলী", TaskSetType.STANDARD),
        Chapter("৪. সমবায় সমিতি ও রাষ্ট্রীয় ব্যবসায়ের মৌলিক ধারণা", TaskSetType.STANDARD),
        Chapter("৫. ব্যবসায়ের আইনগত দিক: পেটেন্ট, ট্রেডমার্ক, কপিরাইট ও ব্যবসায় নীতি", TaskSetType.STANDARD),
        Chapter("৬. ব্যবস্থাপনার পরিচিতি ও হেনরি ফেয়লের ১৪টি প্রশাসনিক মূলনীতি", TaskSetType.STANDARD),
        Chapter("৭. পরিকল্পনা প্রণয়ন ও সিদ্ধান্ত গ্রহণ প্রক্রিয়া (Planning & Decisions)", TaskSetType.STANDARD),
        Chapter("৮. সংগঠন কাঠামো ও কর্মীসংস্থান (Organizing & Staffing)", TaskSetType.STANDARD),
        Chapter("৯. নেতৃত্ব (Leadership Styles), নির্দেশনা ও যোগাযোগ প্রক্রিয়া", TaskSetType.STANDARD),
        Chapter("১০. প্রেষণা তত্ত্ব (Motivation: Maslow & Herzberg)", TaskSetType.STANDARD),
        Chapter("১১. নিয়ন্ত্রণ প্রক্রিয়া ও কৌশল (Controlling, PERT & CPM)", TaskSetType.STANDARD),
        Chapter("১২. DU C Unit ও GST C বিগত বছরের ব্যবসায় সংগঠন প্রশ্নব্যাংক", TaskSetType.STANDARD)
    )

    private val varsityCFinanceMarketingChapters = listOf(
        Chapter("১. অর্থায়নের সূচনা ও আর্থিক ব্যবস্থাপকের লক্ষ্য", TaskSetType.STANDARD),
        Chapter("২. অর্থের সময়মূল্য: বর্তমান মূল্য (PV), ভবিষ্যৎ মূল্য (FV), বার্ষিকী (Annuity)", TaskSetType.STANDARD),
        Chapter("৩. মূলধন বাজেটিং ও কৌশল (NPV, IRR, Payback Period)", TaskSetType.STANDARD),
        Chapter("৪. আর্থিক বাজার: মুদ্রা বাজার ও মূলধন বাজার ইন্সট্রুমেন্টস", TaskSetType.STANDARD),
        Chapter("৫. বাণিজ্যিক ব্যাংক, কেন্দ্রীয় ব্যাংক ও ঋণ নিয়ন্ত্রণ নীতি", TaskSetType.STANDARD),
        Chapter("৬. হস্তান্তরযোগ্য দলিল: চেক, বিল অব এক্সচেঞ্জ ও ডিজিটাল ব্যাংকিং", TaskSetType.STANDARD),
        Chapter("৭. বীমার মূলনীতি: জীবন বীমা, নৌ বীমা ও অগ্নি বীমা", TaskSetType.STANDARD),
        Chapter("৮. বিপণনের পরিচিতি ও বিপণন মিশ্রণ (Product, Price, Place, Promotion)", TaskSetType.STANDARD),
        Chapter("৯. বাজার বিভক্তিকরণ ও ভোক্তা আচরণ বিশ্লেষণ", TaskSetType.STANDARD),
        Chapter("১০. পণ্য জীবনচক্র (Product Life Cycle) ও মূল্য নির্ধারণ", TaskSetType.STANDARD),
        Chapter("১১. DU C Unit ফিন্যান্স ও মার্কেটিং স্পেশাল মডেল টেস্ট", TaskSetType.STANDARD)
    )

    private val varsityCBusinessEnglishChapters = listOf(
        Chapter("১. Business Vocabulary, Commercial Terms & Collocations", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("২. Parts of Speech, Nouns, Pronouns & Determiners", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৩. Subject-Verb Agreement & Sentence Structure", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৪. Right Form of Verbs, Tenses & Conditionals", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৫. Appropriate Prepositions (Business Context)", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৬. Synonyms, Antonyms & Word Power for C Unit", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৭. Reading Comprehension: Business & Economic Passages", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৮. Error Detection & Sentence Completion in Business English", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৯. DU C Unit Written English: Short Paragraph & Translation", TaskSetType.VARSITY_STANDARD)
    )

    private val varsityCBanglaChapters = listOf(
        Chapter("১. নির্বাচিত গদ্য ও কবিতা (উচ্চ মাধ্যমিক টেক্সটবুক সাহিত্য)", TaskSetType.BANGLA_GRAMMAR),
        Chapter("২. বাংলা বানানের নিয়ম ও ন-ত্ব ও ষ-ত্ব বিধান", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৩. বাংলা ব্যাকরণিক শব্দশ্রেণি, পদ ও শব্দ গঠন (উপসর্গ, প্রত্যয়)", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৪. সন্ধি ও সমাস নির্ণয় (বাণিজ্য ইউনিট ফ্রিকোয়েন্ট শব্দাবলী)", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৫. বাক্য শুদ্ধিকরণ, প্রয়োগ-অপপ্রয়োগ ও বাচ্য পরিবর্তন", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৬. সমার্থক শব্দ, বিপরীত শব্দ, বাগধারা ও পারিভাষিক শব্দ", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৭. DU C Unit বিগত ২০ বছরের বাংলা প্রশ্নব্যাংক ও মডেল টেস্ট", TaskSetType.VARSITY_STANDARD)
    )

    val varsityCSubjects = listOf(
        Subject("হিসাববিজ্ঞান ১ম পত্র (Accounting 1st Paper)", "Accounting", 0xFF0D9488, 0xFF0F766E, "📒", varsityCAccounting1Chapters),
        Subject("হিসাববিজ্ঞান ২য় পত্র (Accounting 2nd Paper)", "Accounting", 0xFF0F766E, 0xFF115E59, "📑", varsityCAccounting2Chapters),
        Subject("ব্যবসায় সংগঠন ও ব্যবস্থাপনা (Business Org & Mgmt)", "Management", 0xFF3B82F6, 0xFF1D4ED8, "🏢", varsityCManagementChapters),
        Subject("ফিন্যান্স, ব্যাংকিং, বীমা ও মার্কেটিং", "Finance & Marketing", 0xFFF59E0B, 0xFFB45309, "💰", varsityCFinanceMarketingChapters),
        Subject("Business English (DU C Unit & GST C)", "English", 0xFF6366F1, 0xFF4338CA, "💼", varsityCBusinessEnglishChapters),
        Subject("বাংলা ১ম ও ২য় পত্র (বাণিজ্য ইউনিট)", "Bangla", 0xFF10B981, 0xFF059669, "🇧🇩", varsityCBanglaChapters)
    )

    // --- 7. VARSITY 'D' UNIT MODULES (COMBINED / FACULTY CHANGE & IBA - FULL RESEARCH) ---
    private val varsityDAdvancedEnglishChapters = listOf(
        Chapter("১. Advanced Sentence Structure, Inversion, Subjunctive & Parallelism", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("২. High-Frequency Vocabulary, Word Roots, Prefixes & Suffixes", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৩. Contextual Synonyms, Antonyms & Double-Blank Sentence Completion", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৪. Critical Reasoning: Argument Structure, Assumptions & Inferences", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৫. Verbal Analogies: Relationship Types & Bridge Sentences", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৬. Reading Comprehension: Speed Reading, Central Idea & Tone", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৭. Pinpoint Error Spotting & Sentence Improvement (IBA Pattern)", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৮. Idioms, Phrasal Verbs & Preposition Collocations", TaskSetType.ENGLISH_GRAMMAR),
        Chapter("৯. Analytical Essay & Precise Writing Practice (D Unit Written)", TaskSetType.VARSITY_STANDARD)
    )

    private val varsityDBanglaChapters = listOf(
        Chapter("১. বাংলা ব্যাকরণ: ধ্বনিতত্ত্ব, ন-ত্ব ও ষ-ত্ব বিধান, সন্ধি ও সমাস", TaskSetType.BANGLA_GRAMMAR),
        Chapter("২. পদ প্রকরণ, উপসর্গ, প্রত্যয় ও শব্দ গঠন কৌশল", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৩. বাক্য রূপান্তর, বাক্যের গুণ ও বাক্য সংকোচন (এককথায় প্রকাশ)", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৪. পারিভাষিক শব্দাবলী, প্রবাদ-প্রবচন ও বাগধারা", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৫. বাংলা ভাষা ও সাহিত্যের ইতিবৃত্ত: প্রাচীন, মধ্য ও আধুনিক যুগ", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৬. বিশিষ্ট কবি-সাহিত্যিকদের কালজয়ী সাহিত্যকর্ম ও উক্তি", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৭. উচ্চ মাধ্যমিক বাংলা টেক্সটবুক নির্বাচিত সাহিত্য", TaskSetType.BANGLA_GRAMMAR),
        Chapter("৮. বিগত ২০ বছরের বিশ্ববিদ্যালয় 'D' ইউনিট ও বিভাগ পরিবর্তন প্রশ্নব্যাংক", TaskSetType.VARSITY_STANDARD)
    )

    private val varsityDMathAnalyticalChapters = listOf(
        Chapter("১. Number Properties, Divisibility, Factors, Multiples, LCM & HCF", TaskSetType.STANDARD),
        Chapter("২. Fractions, Decimals, Ratio, Proportion & Variation", TaskSetType.STANDARD),
        Chapter("৩. Percentage, Profit, Loss, Discount & Mark-up Calculations", TaskSetType.STANDARD),
        Chapter("৪. Simple & Compound Interest, Installment Calculations", TaskSetType.STANDARD),
        Chapter("৫. Average, Weighted Average, Mixture & Alligation Problems", TaskSetType.STANDARD),
        Chapter("৬. Work & Time, Pipes & Cisterns, Unitary Method", TaskSetType.STANDARD),
        Chapter("৭. Speed, Time, Distance, Relative Speed, Trains & Boats", TaskSetType.STANDARD),
        Chapter("৮. Basic Algebra: Equations, Systems of Equations, Indices & Logarithms", TaskSetType.STANDARD),
        Chapter("৯. Inequalities, Absolute Values & Function Basics", TaskSetType.STANDARD),
        Chapter("১০. Basic Geometry: Lines, Angles, Triangles, Circles & Quadrilaterals", TaskSetType.STANDARD),
        Chapter("১১. Mensuration (2D & 3D Area, Perimeter, Volume & Surface Area)", TaskSetType.STANDARD),
        Chapter("১২. Analytical Puzzles, Seating Arrangement & Ranking Problems", TaskSetType.STANDARD),
        Chapter("১৩. Logical Deduction, Syllogisms, Venn Diagrams & Data Sufficiency", TaskSetType.STANDARD),
        Chapter("১৪. বিগত ২০ বছরের IBA (DU/JU) ও বিশ্ববিদ্যালয় বিভাগ পরিবর্তন গণিত প্রশ্নব্যাংক", TaskSetType.STANDARD)
    )

    private val varsityDGkChapters = listOf(
        Chapter("১. বাংলাদেশ পরিচিতি: ভূপ্রকৃতি, সীমারেখা, দ্বীপ, নদী ও প্রাকৃতিক সম্পদ", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("২. বাঙালির স্বাধিকার আন্দোলন: ভাষা আন্দোলন (১৯৫২) থেকে মুক্তিযুদ্ধ (১৯৭১)", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৩. বাংলাদেশের সংবিধান, মৌলিক অধিকার, সংসদ ও বিচার বিভাগ", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৪. জাতীয় অর্থনীতি, বাজেট, মেগা প্রকল্প ও সাম্প্রতিক উন্নয়ন চিত্র", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৫. আন্তর্জাতিক পরিচিতি: মহাদেশ, মহাসাগর, প্রণালী, চ্যানেল ও সীমারেখা", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৬. জাতিসংঘ, বিশ্বব্যাংক, আইএমএফ ও শীর্ষ আন্তর্জাতিক সংস্থাসমূহ", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৭. বৈশ্বিক পরাশক্তি রাজনীতি, ঐতিহাসিক সম্মেলন ও পরিবেশ চুক্তি", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৮. সাম্প্রতিক বাংলাদেশ ও আন্তর্জাতিক শীর্ষ সংবাদ ও ঘটনাবলী", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("৯. সাধারণ বিজ্ঞান ও দৈনন্দিন তথ্যপ্রযুক্তি (ICT) মৌলিক ধারণা", TaskSetType.MEDICAL_GK_ENGLISH),
        Chapter("১০. বিশ্ববিদ্যালয় 'D' ইউনিট ও IBA জিকে স্পেশাল মডেল টেস্ট", TaskSetType.MEDICAL_GK_ENGLISH)
    )

    val varsityDSubjects = listOf(
        Subject("Advanced English & Critical Reasoning (Varsity D / IBA)", "English", 0xFF6366F1, 0xFF4338CA, "🎯", varsityDAdvancedEnglishChapters),
        Subject("বাংলা ব্যাকরণ ও সাহিত্য (বিভাগ পরিবর্তন / D Unit)", "Bangla", 0xFFEC4899, 0xFFBE185D, "📚", varsityDBanglaChapters),
        Subject("Basic Mathematics & Analytical Ability", "Math & Analytical", 0xFFF59E0B, 0xFFD97706, "🧮", varsityDMathAnalyticalChapters),
        Subject("বাংলাদেশ ও আন্তর্জাতিক বিষয়াবলী (D Unit Special)", "General Knowledge", 0xFF10B981, 0xFF047857, "🌐", varsityDGkChapters)
    )

    fun getModulesForTrack(track: PrepTrack): List<ModuleSection> {
        return when (track) {
            PrepTrack.HSC -> listOf(
                ModuleSection(
                    id = "ebi",
                    title = "EBI PROGRESS",
                    subtitle = "Bangla 1st, 2nd, English 1st, 2nd & ICT",
                    colorStartHex = 0xFFEF4444,
                    colorEndHex = 0xFF3B82F6,
                    iconEmoji = "📚",
                    subjects = hscEbiSubjects
                ),
                ModuleSection(
                    id = "pcmb",
                    title = "SCIENCE (PCMB)",
                    subtitle = "Physics, Chemistry, Higher Math & Biology (Both Papers)",
                    colorStartHex = 0xFF0284C7,
                    colorEndHex = 0xFF10B981,
                    iconEmoji = "🔬",
                    subjects = hscPcmbSubjects
                )
            )

            PrepTrack.VARSITY_A -> listOf(
                ModuleSection(
                    id = "varsity_phys_math",
                    title = "VARSITY PHYSICS & HIGHER MATH",
                    subtitle = "All 21 Chapters Physics + All 20 Chapters Math (Speed MCQ & Short Written)",
                    colorStartHex = 0xFF0284C7,
                    colorEndHex = 0xFFF59E0B,
                    iconEmoji = "⚡",
                    subjects = varsityPhysicsMathSubjects
                ),
                ModuleSection(
                    id = "varsity_chem_bio_elect",
                    title = "VARSITY CHEMISTRY, BIOLOGY & ELECTIVES",
                    subtitle = "All 10 Chp Chemistry + All 24 Chp Biology + DU Ka English, Bangla & GK",
                    colorStartHex = 0xFF10B981,
                    colorEndHex = 0xFF8B5CF6,
                    iconEmoji = "🧪",
                    subjects = varsityChemBioElectiveSubjects
                )
            )

            PrepTrack.VARSITY_B -> listOf(
                ModuleSection(
                    id = "varsity_b_lang",
                    title = "বাংলা ও ইংরেজি প্রস্তুতি (B Unit)",
                    subtitle = "বাংলা ১ম ও ২য় পত্র (২৪ অধ্যায়) + English Grammar, Lit & Vocab (২০ অধ্যায়)",
                    colorStartHex = 0xFFEC4899,
                    colorEndHex = 0xFF6366F1,
                    iconEmoji = "📖",
                    subjects = varsityBSubjects.take(4)
                ),
                ModuleSection(
                    id = "varsity_b_gk",
                    title = "সাধারণ জ্ঞান, আইসিটি ও মানসিক দক্ষতা (B Unit)",
                    subtitle = "বাংলাদেশ (১৮ অধ্যায়), আন্তর্জাতিক (১৪ অধ্যায়) ও বেসিক আইসিটি-মানসিক দক্ষতা (৮ অধ্যায়)",
                    colorStartHex = 0xFF10B981,
                    colorEndHex = 0xFFF59E0B,
                    iconEmoji = "🏛️",
                    subjects = varsityBSubjects.drop(4)
                )
            )

            PrepTrack.VARSITY_C -> listOf(
                ModuleSection(
                    id = "varsity_c_core",
                    title = "হিসাববিজ্ঞান ও ব্যবসায় ব্যবস্থাপনা (C Unit)",
                    subtitle = "হিসাববিজ্ঞান ১ম ও ২য় পত্র (২০ অধ্যায়) + ব্যবসায় সংগঠন ও ব্যবস্থাপনা (১২ অধ্যায়)",
                    colorStartHex = 0xFF0D9488,
                    colorEndHex = 0xFF3B82F6,
                    iconEmoji = "📒",
                    subjects = varsityCSubjects.take(3)
                ),
                ModuleSection(
                    id = "varsity_c_fin_lang",
                    title = "ফিন্যান্স/মার্কেটিং ও ভাষা প্রস্তুতি (C Unit)",
                    subtitle = "ফিন্যান্স ও ব্যাংকিং/মার্কেটিং (১১ অধ্যায়) + Business English ও বাংলা (১৬ অধ্যায়)",
                    colorStartHex = 0xFFF59E0B,
                    colorEndHex = 0xFF6366F1,
                    iconEmoji = "💰",
                    subjects = varsityCSubjects.drop(3)
                )
            )

            PrepTrack.VARSITY_D -> listOf(
                ModuleSection(
                    id = "varsity_d_lang",
                    title = "উচ্চতর ভাষা ও ক্রিটিক্যাল রিজনিং (D Unit / IBA)",
                    subtitle = "Advanced English & Critical Reasoning (৯ অধ্যায়) + বাংলা ব্যাকরণ ও সাহিত্য (৮ অধ্যায়)",
                    colorStartHex = 0xFF6366F1,
                    colorEndHex = 0xFFEC4899,
                    iconEmoji = "🎯",
                    subjects = varsityDSubjects.take(2)
                ),
                ModuleSection(
                    id = "varsity_d_math_gk",
                    title = "গণিত, এনালিটিক্যাল ও সাধারণ জ্ঞান (D Unit / IBA)",
                    subtitle = "Basic Mathematics & Analytical Ability (১৪ অধ্যায়) + সাধারণ জ্ঞান (১০ অধ্যায়)",
                    colorStartHex = 0xFFF59E0B,
                    colorEndHex = 0xFF10B981,
                    iconEmoji = "🧮",
                    subjects = varsityDSubjects.drop(2)
                )
            )

            PrepTrack.ENGINEERING -> listOf(
                ModuleSection(
                    id = "engg_core",
                    title = "ENGINEERING CORE MASTERY",
                    subtitle = "BUET & CKREUT Standards - Math (20 Chp), Physics (21 Chp) & Chemistry (10 Chp)",
                    colorStartHex = 0xFFD97706,
                    colorEndHex = 0xFF0284C7,
                    iconEmoji = "📐",
                    subjects = engineeringSubjects
                )
            )

            PrepTrack.MEDICAL -> listOf(
                ModuleSection(
                    id = "medical_bio",
                    title = "MEDICAL BIOLOGY MASTERY (30 Marks)",
                    subtitle = "Abul Hasan Botany (12 Chp) + Gazi Azmal Zoology (12 Chp) Line-to-Line",
                    colorStartHex = 0xFF84CC16,
                    colorEndHex = 0xFF0D9488,
                    iconEmoji = "🌿",
                    subjects = medicalBioSubjects
                ),
                ModuleSection(
                    id = "medical_chem_phys",
                    title = "MEDICAL CHEMISTRY & PHYSICS (45 Marks)",
                    subtitle = "Hazari Chemistry (10 Chp) + Ishaq Physics (21 Chp) Information Drill",
                    colorStartHex = 0xFF10B981,
                    colorEndHex = 0xFF0284C7,
                    iconEmoji = "🧪",
                    subjects = medicalChemPhysicsSubjects
                ),
                ModuleSection(
                    id = "medical_eng_gk",
                    title = "MEDICAL ENGLISH & GK (25 Marks)",
                    subtitle = "English Grammar (15M) + All GK: ইতিহাস, মুক্তিযুদ্ধ, বঙ্গবন্ধু, সংবিধান, ভূগোল ও আন্তর্জাতিক (10M)",
                    colorStartHex = 0xFF6366F1,
                    colorEndHex = 0xFFF43F5E,
                    iconEmoji = "🏛️",
                    subjects = medicalEnglishGkSubjects
                )
            )
        }
    }
}
