package jt.projects.gbpopularlibs.core.utils

// TAGS
const val DEBUG_TAG = "@@@"

// SCREENS
enum class SCREENS(val ID: String) {
    USERS("USERS_SCREEN"),
    USER_CARD("USER_CARD_SCREEN"),
    COUNTERS_MVP("COUNTERS_MVP_SCREEN")
}

// анимации RECYCLER VIEW
const val DURATION_ITEM_ANIMATOR: Long = 300

// Bundle key
const val USER_ENTITY_BUNDLE_KEY = "USER_ENTITY_BUNDLE_KEY"