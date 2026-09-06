package com.example.model

data class StudyTask(
    val id: String,
    val label: String,
    val weight: Int
)

enum class TaskSetType {
    STANDARD,
    BANGLA_GRAMMAR,
    ENGLISH_GRAMMAR,
    SIMPLE_READING_WRITING,
    EFT_UNIT_PASSAGE,
    UNSEEN_PRACTICE,
    VARSITY_STANDARD,
    ENGINEERING_STANDARD,
    MEDICAL_STANDARD,
    MEDICAL_GK_ENGLISH
}

object TaskSets {
    val standard = listOf(
        StudyTask("class", "1. Class+Note taking", 20),
        StudyTask("note", "2. Note Revise", 10),
        StudyTask("book", "3. BOOK", 20),
        StudyTask("mcq", "4. MCQ PRACTICE", 20),
        StudyTask("cq", "5. CQ PRACTICE", 25),
        StudyTask("revise1", "6. Revise (1st)", 3),
        StudyTask("revise2", "7. Revise (2nd)", 1),
        StudyTask("revise3", "8. Revise (3rd)", 1)
    )

    val banglaGrammar = listOf(
        StudyTask("class", "1. Class / Concept", 30),
        StudyTask("rules", "2. Rules Study", 30),
        StudyTask("practice", "3. Practice (Board Question)", 35),
        StudyTask("revise1", "4. Revise (1st)", 3),
        StudyTask("revise2", "5. Revise (2nd)", 1),
        StudyTask("revise3", "6. Revise (3rd)", 1)
    )

    val englishGrammar = listOf(
        StudyTask("class_rules", "1. Class + Rules Study", 45),
        StudyTask("practice", "2. Practice (Board/Test Paper)", 50),
        StudyTask("revise1", "3. Revise (1st)", 3),
        StudyTask("revise2", "4. Revise (2nd)", 1),
        StudyTask("revise3", "5. Revise (3rd)", 1)
    )

    val simpleReadingWriting = listOf(
        StudyTask("first_read", "1. First Time Read / Study", 95),
        StudyTask("revise1", "2. Revise (1st)", 3),
        StudyTask("revise2", "3. Revise (2nd)", 1),
        StudyTask("revise3", "4. Revise (3rd)", 1)
    )

    val eftUnitPassage = listOf(
        StudyTask("class_vocab", "1. Text Reading + Vocab", 30),
        StudyTask("mcq_cq", "2. Q1 & Q2 Practice (MCQ/Comprehension)", 35),
        StudyTask("flow_summary", "3. Q3 & Q4 Practice (Flow Chart/Summary)", 30),
        StudyTask("revise1", "4. Revise (1st)", 3),
        StudyTask("revise2", "5. Revise (2nd)", 1),
        StudyTask("revise3", "6. Revise (3rd)", 1)
    )

    val unseenPractice = listOf(
        StudyTask("practice", "1. Practice Exercises", 95),
        StudyTask("revise1", "2. Revise (1st)", 3),
        StudyTask("revise2", "3. Revise (2nd)", 1),
        StudyTask("revise3", "4. Revise (3rd)", 1)
    )

    val varsityStandard = listOf(
        StudyTask("concept_formula", "1. Concept & Shortcut Formulas", 20),
        StudyTask("du_qb", "2. DU 'Ka' Question Bank (20 Yrs)", 30),
        StudyTask("gst_qb", "3. GST & Other Varsity QB", 20),
        StudyTask("speed_drill", "4. 45-Sec Speed MCQ Drill", 20),
        StudyTask("written", "5. Written Part Practice", 7),
        StudyTask("revise", "6. Rapid Final Revision", 3)
    )

    val engineeringStandard = listOf(
        StudyTask("theory_lecture", "1. Deep Theory & Lecture", 15),
        StudyTask("text_math", "2. Main Book Hard Math Deep-Dive", 20),
        StudyTask("concept_book", "3. Concept Book / Practice Sheet", 20),
        StudyTask("buet_qb", "4. BUET Past 25 Years QB", 25),
        StudyTask("ckreut_qb", "5. CKREUT Question Bank", 15),
        StudyTask("written_mock", "6. Written Speed Mock Test", 5)
    )

    val medicalStandard = listOf(
        StudyTask("line_reading", "1. Main Book Line-by-Line Reading", 30),
        StudyTask("digest_study", "2. Medical Digest & Pointer Sheet", 20),
        StudyTask("mats_qb", "3. Medical Past 20 Years QB (MATS)", 25),
        StudyTask("varsity_qb", "4. Dental & Varsity Question Bank", 15),
        StudyTask("negative_mock", "5. Negative Marking (-0.25) Mock", 7),
        StudyTask("rapid_memory", "6. 3-Round Rapid Memory Revision", 3)
    )

    val medicalGkEnglish = listOf(
        StudyTask("core_gk", "1. Core Topics & High-Yield Memorization", 40),
        StudyTask("med_qb", "2. Medical Past 20 Years QB", 30),
        StudyTask("bcs_model", "3. BCS & High-Difficulty Practice", 25),
        StudyTask("revision", "4. Rapid High-Frequency Revision", 5)
    )

    fun getTasks(type: TaskSetType): List<StudyTask> = when (type) {
        TaskSetType.STANDARD -> standard
        TaskSetType.BANGLA_GRAMMAR -> banglaGrammar
        TaskSetType.ENGLISH_GRAMMAR -> englishGrammar
        TaskSetType.SIMPLE_READING_WRITING -> simpleReadingWriting
        TaskSetType.EFT_UNIT_PASSAGE -> eftUnitPassage
        TaskSetType.UNSEEN_PRACTICE -> unseenPractice
        TaskSetType.VARSITY_STANDARD -> varsityStandard
        TaskSetType.ENGINEERING_STANDARD -> engineeringStandard
        TaskSetType.MEDICAL_STANDARD -> medicalStandard
        TaskSetType.MEDICAL_GK_ENGLISH -> medicalGkEnglish
    }
}

data class Chapter(
    val title: String,
    val taskType: TaskSetType = TaskSetType.STANDARD,
    val driveLink: String = "",
    val extraNote: String = "",
    val customTasks: List<StudyTask>? = null
) {
    fun getEffectiveTasks(): List<StudyTask> =
        if (!customTasks.isNullOrEmpty()) customTasks else TaskSets.getTasks(taskType)
}

data class Subject(
    val name: String,
    val category: String, // e.g. "EBI", "PCMB", "Core Science", "General Knowledge"
    val colorStartHex: Long,
    val colorEndHex: Long,
    val iconEmoji: String,
    val chapters: List<Chapter>
)

data class ModuleSection(
    val id: String,
    val title: String,
    val subtitle: String,
    val iconEmoji: String,
    val colorStartHex: Long,
    val colorEndHex: Long,
    val subjects: List<Subject>
)
