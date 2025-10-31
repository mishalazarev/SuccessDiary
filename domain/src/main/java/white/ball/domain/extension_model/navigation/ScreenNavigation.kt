package white.ball.domain.extension_model.navigation

enum class ScreenNavigation (val route: String) {
    // Main
    MAIN_SCREEN("main_screen"),
    NOTE_BOOK_SCREEN("note_book_screen"),
    PROFILE_SCREEN("profile_screen"),
    TASK_LIST_SCREEN("task_list_screen"),
    // Secondary
    ARCHIVE_SCREEN("archive_screen"),
    CREATE_SCREEN("create_screen"),
    EDIT_SCREEN("edit_screen"),
    SORTED_SCREEN("sorted_screen"),
}