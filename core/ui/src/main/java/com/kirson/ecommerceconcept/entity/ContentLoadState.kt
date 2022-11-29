package com.kirson.ecommerceconcept.entity

/**
 * A state which represents a loading progress of a whole screen content.
 * Used to render progress/error of *initial* screen load state.
 * This is not a state to represent progress/error state of subsequent screen actions.
 *
 * Examples:
 *  - loading of user profile: uses ContentLoadState
 *  - reacting to user pressing Login button: should not use ContentLoadState,
 *    because this is an action when content is already displayed.
 *    Progress/error is rendered differently in this case (usually)
 */
sealed class ContentLoadState {
  object NotStarted : ContentLoadState()
  object Loading : ContentLoadState()
  object Ready : ContentLoadState()
  data class Error(val error: UiError, val isRefreshInProgress: Boolean) : ContentLoadState()
}
