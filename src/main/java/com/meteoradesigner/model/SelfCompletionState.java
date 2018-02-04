package com.meteoradesigner.model;

/**
 * SelfCompletionState - not the entity, obligatory self completion state for a task.
 */
public enum SelfCompletionState {
    SELF_COMPLETION_STATE_DISABLED,
    SELF_COMPLETION_STATE_ENABLED,
    SELF_COMPLETION_STATE_ACTIVE,
    SELF_COMPLETION_STATE_FROZEN,
    SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES,
    SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY,
    SELF_COMPLETION_STATE_COMPLETED,
    SELF_COMPLETION_STATE_TENTATIVE,
}