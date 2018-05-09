package com.meteoradesigner.util.statemachine;

import com.meteoradesigner.model.SelfCompletionState;
import com.meteoradesigner.model.SelfCompletionStateEvents;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;

import static com.meteoradesigner.model.SelfCompletionState.SELF_COMPLETION_STATE_ACTIVE;
import static com.meteoradesigner.model.SelfCompletionState.SELF_COMPLETION_STATE_COMPLETED;
import static com.meteoradesigner.model.SelfCompletionState.SELF_COMPLETION_STATE_DISABLED;
import static com.meteoradesigner.model.SelfCompletionState.SELF_COMPLETION_STATE_ENABLED;
import static com.meteoradesigner.model.SelfCompletionState.SELF_COMPLETION_STATE_FROZEN;
import static com.meteoradesigner.model.SelfCompletionState
        .SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES;
import static com.meteoradesigner.model.SelfCompletionState
        .SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY;
import static com.meteoradesigner.model.SelfCompletionState.SELF_COMPLETION_STATE_NEW;
import static com.meteoradesigner.model.SelfCompletionState.SELF_COMPLETION_STATE_TENTATIVE;


//TODO not String actions and events can cause errors.
//TODO not adding special event for each transition can cause errors, but logically better to use only one event
// for all transitions.
//TODO fix docs from New possible only to Disabled or Enabled
//TODO fix docs from Disabled possible only to Enabled

/**
 * This @code{TaskSelfCompletionStateMachine} class represents configuration
 * of @code{StateMachine} for handling @code{SelfCompletionState}s.
 */
@Configuration
@EnableStateMachine
public class TaskSelfCompletionStateMachine
        extends StateMachineConfigurerAdapter<SelfCompletionState, SelfCompletionStateEvents> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(StateMachineStateConfigurer<SelfCompletionState, SelfCompletionStateEvents> states)
            throws Exception {
        states.withStates()
              .initial(SELF_COMPLETION_STATE_NEW)
              .state(SELF_COMPLETION_STATE_DISABLED)
              .state(SELF_COMPLETION_STATE_ENABLED)
              .state(SELF_COMPLETION_STATE_FROZEN)
              .state(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
              .state(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
              .state(SELF_COMPLETION_STATE_ACTIVE)
              .state(SELF_COMPLETION_STATE_COMPLETED)
              .state(SELF_COMPLETION_STATE_TENTATIVE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<SelfCompletionState, SelfCompletionStateEvents> transitions)
            throws Exception {
        transitions

                //to Disabled
                .withLocal()
                .source(SELF_COMPLETION_STATE_NEW)
                .target(SELF_COMPLETION_STATE_DISABLED)
                .event(SelfCompletionStateEvents.SET_DISABLED)
                .action(fromNewToDisabledAction())
                .guard(fromNewToDisabledGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_DISABLED)
                .target(SELF_COMPLETION_STATE_DISABLED)
                .event(SelfCompletionStateEvents.SET_DISABLED)
                .action(fromDisabledToDisabledAction())
                .guard(fromDisabledToDisabledGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN)
                .target(SELF_COMPLETION_STATE_DISABLED)
                .event(SelfCompletionStateEvents.SET_DISABLED)
                .action(fromFrozenToDisabledAction())
                .guard(fromFrozenToDisabledGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_ACTIVE)
                .target(SELF_COMPLETION_STATE_DISABLED)
                .event(SelfCompletionStateEvents.SET_DISABLED)
                .action(fromActiveToDisabledAction())
                .guard(fromActiveToDisabledGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_ENABLED)
                .target(SELF_COMPLETION_STATE_DISABLED)
                .event(SelfCompletionStateEvents.SET_DISABLED)
                .action(fromEnabledToDisabledAction())
                .guard(fromEnabledToDisabledGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_COMPLETED)
                .target(SELF_COMPLETION_STATE_DISABLED)
                .event(SelfCompletionStateEvents.SET_DISABLED)
                .action(fromCompletedToDisabledAction())
                .guard(fromCompletedToDisabledGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .target(SELF_COMPLETION_STATE_DISABLED)
                .event(SelfCompletionStateEvents.SET_DISABLED)
                .action(fromFrozenWithForciblyUnlockedRelativesToDisabledAction())
                .guard(fromFrozenWithForciblyUnlockedRelativesToDisabledGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .target(SELF_COMPLETION_STATE_DISABLED)
                .event(SelfCompletionStateEvents.SET_DISABLED)
                .action(fromFrozenWithForciblyUnlockedRelativesByToDisabledAction())
                .guard(fromFrozenWithForciblyUnlockedRelativesByToDisabledGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_TENTATIVE)
                .target(SELF_COMPLETION_STATE_DISABLED)
                .event(SelfCompletionStateEvents.SET_DISABLED)
                .action(fromTentativeToDisabledAction())
                .guard(fromTentativeToDisabledGuard())
                .and()

                //to Enabled
                .withLocal()
                .source(SELF_COMPLETION_STATE_NEW)
                .target(SELF_COMPLETION_STATE_ENABLED)
                .event(SelfCompletionStateEvents.SET_ENABLED)
                .action(fromNewToEnabledAction())
                .guard(fromNewToEnabledGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_DISABLED)
                .target(SELF_COMPLETION_STATE_ENABLED)
                .event(SelfCompletionStateEvents.SET_ENABLED)
                .action(fromDisabledToEnabledAction())
                .guard(fromDisabledToEnabledGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_TENTATIVE)
                .target(SELF_COMPLETION_STATE_ENABLED)
                .event(SelfCompletionStateEvents.SET_ENABLED)
                .action(fromTentativeToEnabledAction())
                .guard(fromTentativeToEnabledGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN)
                .target(SELF_COMPLETION_STATE_ENABLED)
                .event(SelfCompletionStateEvents.SET_ENABLED)
                .action(fromFrozenToEnabledAction())
                .guard(fromFrozenToEnabledGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_ACTIVE)
                .target(SELF_COMPLETION_STATE_ENABLED)
                .event(SelfCompletionStateEvents.SET_ENABLED)
                .action(fromActiveToEnabledAction())
                .guard(fromActiveToEnabledGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_COMPLETED)
                .target(SELF_COMPLETION_STATE_ENABLED)
                .event(SelfCompletionStateEvents.SET_ENABLED)
                .action(fromCompletedToEnabledAction())
                .guard(fromCompletedToEnabledGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_ENABLED)
                .target(SELF_COMPLETION_STATE_ENABLED)
                .event(SelfCompletionStateEvents.SET_ENABLED)
                .action(fromEnabledToEnabledAction())
                .guard(fromEnabledToEnabledGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .target(SELF_COMPLETION_STATE_ENABLED)
                .event(SelfCompletionStateEvents.SET_ENABLED)
                .action(fromFrozenWithForciblyUnlockedRelativesByToEnabledAction())
                .guard(fromFrozenWithForciblyUnlockedRelativesByToEnabledGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .target(SELF_COMPLETION_STATE_ENABLED)
                .event(SelfCompletionStateEvents.SET_ENABLED)
                .action(fromFrozenWithForciblyUnlockedRelativesToEnabledAction())
                .guard(fromFrozenWithForciblyUnlockedRelativesToEnabledGuard())
                .and()

                //to Frozen
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN)
                .target(SELF_COMPLETION_STATE_FROZEN)
                .event(SelfCompletionStateEvents.SET_FROZEN)
                .action(fromFrozenToFrozenAction())
                .guard(fromFrozenToFrozenGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_ENABLED)
                .target(SELF_COMPLETION_STATE_FROZEN)
                .event(SelfCompletionStateEvents.SET_FROZEN)
                .action(fromEnabledToFrozenAction())
                .guard(fromEnabledToFrozenGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .target(SELF_COMPLETION_STATE_FROZEN)
                .event(SelfCompletionStateEvents.SET_FROZEN)
                .action(fromFrozenWithForciblyUnlockedRelativesByToFrozenAction())
                .guard(fromFrozenWithForciblyUnlockedRelativesByToFrozenGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .target(SELF_COMPLETION_STATE_FROZEN)
                .event(SelfCompletionStateEvents.SET_FROZEN)
                .action(fromFrozenWithForciblyUnlockedRelativesToFrozenAction())
                .guard(fromFrozenWithForciblyUnlockedRelativesToFrozenGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_ACTIVE)
                .target(SELF_COMPLETION_STATE_FROZEN)
                .event(SelfCompletionStateEvents.SET_FROZEN)
                .action(fromActiveToFrozenAction())
                .guard(fromActiveToFrozenGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_TENTATIVE)
                .target(SELF_COMPLETION_STATE_FROZEN)
                .event(SelfCompletionStateEvents.SET_FROZEN)
                .action(fromTentativeToFrozenAction())
                .guard(fromTentativeToFrozenGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_COMPLETED)
                .target(SELF_COMPLETION_STATE_FROZEN)
                .event(SelfCompletionStateEvents.SET_FROZEN)
                .action(fromCompletedToFrozenAction())
                .guard(fromCompletedToFrozenGuard())
                .and()

                //to FrozenWithForciblyUnlockedRelativesBy
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .target(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .event(SelfCompletionStateEvents.SET_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .action(fromFrozenWithForciblyUnlockedRelativesByToFrozenWithForciblyUnlockedRelativesByAction())
                .guard(fromFrozenWithForciblyUnlockedRelativesByToFrozenWithForciblyUnlockedRelativesByGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_ENABLED)
                .target(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .event(SelfCompletionStateEvents.SET_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .action(fromEnabledToFrozenWithForciblyUnlockedRelativesByAction())
                .guard(fromEnabledToFrozenWithForciblyUnlockedRelativesByGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN)
                .target(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .event(SelfCompletionStateEvents.SET_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .action(fromFrozenToFrozenWithForciblyUnlockedRelativesByAction())
                .guard(fromFrozenToFrozenWithForciblyUnlockedRelativesByGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .target(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .event(SelfCompletionStateEvents.SET_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .action(fromFrozenWithForciblyUnlockedRelativesToFrozenWithForciblyUnlockedRelativesByAction())
                .guard(fromFrozenWithForciblyUnlockedRelativesToFrozenWithForciblyUnlockedRelativesByGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_ACTIVE)
                .target(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .event(SelfCompletionStateEvents.SET_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .action(fromActiveToFrozenWithForciblyUnlockedRelativesByAction())
                .guard(fromActiveToFrozenWithForciblyUnlockedRelativesByGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_TENTATIVE)
                .target(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .event(SelfCompletionStateEvents.SET_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .action(fromTentativeToFrozenWithForciblyUnlockedRelativesByAction())
                .guard(fromTentativeToFrozenWithForciblyUnlockedRelativesByGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_COMPLETED)
                .target(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .event(SelfCompletionStateEvents.SET_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .action(fromCompletedToFrozenWithForciblyUnlockedRelativesByAction())
                .guard(fromCompletedToFrozenWithForciblyUnlockedRelativesByGuard())
                .and()

                //to FrozenWithForciblyUnlockedRelatives
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .target(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .event(SelfCompletionStateEvents.SET_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .action(fromFrozenWithForciblyUnlockedRelativesToFrozenWithForciblyUnlockedRelativesAction())
                .guard(fromFrozenWithForciblyUnlockedRelativesToFrozenWithForciblyUnlockedRelativesGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_ENABLED)
                .target(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .event(SelfCompletionStateEvents.SET_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .action(fromEnabledToFrozenWithForciblyUnlockedRelativesAction())
                .guard(fromEnabledToFrozenWithForciblyUnlockedRelativesGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN)
                .target(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .event(SelfCompletionStateEvents.SET_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .action(fromFrozenToFrozenWithForciblyUnlockedRelativesAction())
                .guard(fromFrozenToFrozenWithForciblyUnlockedRelativesGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .target(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .event(SelfCompletionStateEvents.SET_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .action(fromFrozenWithForciblyUnlockedRelativesByToFrozenWithForciblyUnlockedRelativesAction())
                .guard(fromFrozenWithForciblyUnlockedRelativesByToFrozenWithForciblyUnlockedRelativesGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_ACTIVE)
                .target(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .event(SelfCompletionStateEvents.SET_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .action(fromActiveToFrozenWithForciblyUnlockedRelativesAction())
                .guard(fromActiveToFrozenWithForciblyUnlockedRelativesGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_TENTATIVE)
                .target(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .event(SelfCompletionStateEvents.SET_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .action(fromTentativeToFrozenWithForciblyUnlockedRelativesAction())
                .guard(fromTentativeToFrozenWithForciblyUnlockedRelativesGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_COMPLETED)
                .target(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .event(SelfCompletionStateEvents.SET_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .action(fromCompletedToFrozenWithForciblyUnlockedRelativesAction())
                .guard(fromCompletedToFrozenWithForciblyUnlockedRelativesGuard())
                .and()

                //to Active
                .withLocal()
                .source(SELF_COMPLETION_STATE_ACTIVE)
                .target(SELF_COMPLETION_STATE_ACTIVE)
                .event(SelfCompletionStateEvents.SET_ACTIVE)
                .action(fromActiveToActiveAction())
                .guard(fromActiveToActiveGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_ENABLED)
                .target(SELF_COMPLETION_STATE_ACTIVE)
                .event(SelfCompletionStateEvents.SET_ACTIVE)
                .action(fromEnabledToActiveAction())
                .guard(fromEnabledToActiveGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN)
                .target(SELF_COMPLETION_STATE_ACTIVE)
                .event(SelfCompletionStateEvents.SET_ACTIVE)
                .action(fromFrozenToActiveAction())
                .guard(fromFrozenToActiveGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .target(SELF_COMPLETION_STATE_ACTIVE)
                .event(SelfCompletionStateEvents.SET_ACTIVE)
                .action(fromFrozenWithForciblyUnlockedRelativesByToActiveAction())
                .guard(fromFrozenWithForciblyUnlockedRelativesByToActiveGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .target(SELF_COMPLETION_STATE_ACTIVE)
                .event(SelfCompletionStateEvents.SET_ACTIVE)
                .action(fromFrozenWithForciblyUnlockedRelativesToActiveAction())
                .guard(fromFrozenWithForciblyUnlockedRelativesToActiveGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_TENTATIVE)
                .target(SELF_COMPLETION_STATE_ACTIVE)
                .event(SelfCompletionStateEvents.SET_ACTIVE)
                .action(fromTentativeToActiveAction())
                .guard(fromTentativeToActiveGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_COMPLETED)
                .target(SELF_COMPLETION_STATE_ACTIVE)
                .event(SelfCompletionStateEvents.SET_ACTIVE)
                .action(fromCompletedToActiveAction())
                .guard(fromCompletedToActiveGuard())
                .and()

                //to Tentative
                .withLocal()
                .source(SELF_COMPLETION_STATE_TENTATIVE)
                .target(SELF_COMPLETION_STATE_TENTATIVE)
                .event(SelfCompletionStateEvents.SET_TENTATIVE)
                .action(fromTentativeToTentativeAction())
                .guard(fromTentativeToTentativeGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_COMPLETED)
                .target(SELF_COMPLETION_STATE_TENTATIVE)
                .event(SelfCompletionStateEvents.SET_TENTATIVE)
                .action(fromCompletedToTentativeAction())
                .guard(fromCompletedToTentativeGuard())
                .and()

                //to Completed
                .withLocal()
                .source(SELF_COMPLETION_STATE_COMPLETED)
                .target(SELF_COMPLETION_STATE_COMPLETED)
                .event(SelfCompletionStateEvents.SET_COMPLETED)
                .action(fromCompletedToCompletedAction())
                .guard(fromCompletedToCompletedGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_ENABLED)
                .target(SELF_COMPLETION_STATE_COMPLETED)
                .event(SelfCompletionStateEvents.SET_COMPLETED)
                .action(fromEnabledToCompletedAction())
                .guard(fromEnabledToCompletedGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN)
                .target(SELF_COMPLETION_STATE_COMPLETED)
                .event(SelfCompletionStateEvents.SET_COMPLETED)
                .action(fromFrozenToCompletedAction())
                .guard(fromFrozenToCompletedGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES_BY)
                .target(SELF_COMPLETION_STATE_COMPLETED)
                .event(SelfCompletionStateEvents.SET_COMPLETED)
                .action(fromFrozenWithForciblyUnlockedRelativesByToCompletedAction())
                .guard(fromFrozenWithForciblyUnlockedRelativesByToCompletedGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_FROZEN_WITH_FORCIBLY_UNLOCKED_RELATIVES)
                .target(SELF_COMPLETION_STATE_COMPLETED)
                .event(SelfCompletionStateEvents.SET_COMPLETED)
                .action(fromFrozenWithForciblyUnlockedRelativesToCompletedAction())
                .guard(fromFrozenWithForciblyUnlockedRelativesToCompletedGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_ACTIVE)
                .target(SELF_COMPLETION_STATE_COMPLETED)
                .event(SelfCompletionStateEvents.SET_COMPLETED)
                .action(fromActiveToCompletedAction())
                .guard(fromActiveToCompletedGuard())
                .and()
                .withLocal()
                .source(SELF_COMPLETION_STATE_TENTATIVE)
                .target(SELF_COMPLETION_STATE_COMPLETED)
                .event(SelfCompletionStateEvents.SET_COMPLETED)
                .action(fromTentativeToCompletedAction())
                .guard(fromTentativeToCompletedGuard());

    }


    //Actions to Disabled

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromNewToDisabledAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromDisabledToDisabledAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromFrozenToDisabledAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromActiveToDisabledAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromEnabledToDisabledAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromCompletedToDisabledAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesByToDisabledAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesToDisabledAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromTentativeToDisabledAction() {
        return (context) -> {/*add some action*/};
    }

    //Actions to Enabled

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromNewToEnabledAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromDisabledToEnabledAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromEnabledToEnabledAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromFrozenToEnabledAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesByToEnabledAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesToEnabledAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromActiveToEnabledAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromTentativeToEnabledAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromCompletedToEnabledAction() {
        return (context) -> {/*add some action*/};
    }

    //Actions to Frozen

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromFrozenToFrozenAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromEnabledToFrozenAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesByToFrozenAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesToFrozenAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromActiveToFrozenAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromTentativeToFrozenAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromCompletedToFrozenAction() {
        return (context) -> {/*add some action*/};
    }

    //Actions to FrozenWithForciblyUnlockedRelativesBy

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesByToFrozenWithForciblyUnlockedRelativesByAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromEnabledToFrozenWithForciblyUnlockedRelativesByAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenToFrozenWithForciblyUnlockedRelativesByAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesToFrozenWithForciblyUnlockedRelativesByAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromActiveToFrozenWithForciblyUnlockedRelativesByAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromTentativeToFrozenWithForciblyUnlockedRelativesByAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromCompletedToFrozenWithForciblyUnlockedRelativesByAction() {
        return (context) -> {/*add some action*/};
    }

    //Actions to FrozenWithForciblyUnlockedRelativesBy

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesToFrozenWithForciblyUnlockedRelativesAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromEnabledToFrozenWithForciblyUnlockedRelativesAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenToFrozenWithForciblyUnlockedRelativesAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesByToFrozenWithForciblyUnlockedRelativesAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromActiveToFrozenWithForciblyUnlockedRelativesAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromTentativeToFrozenWithForciblyUnlockedRelativesAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromCompletedToFrozenWithForciblyUnlockedRelativesAction() {
        return (context) -> {/*add some action*/};
    }

    //Actions to Active

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromActiveToActiveAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromEnabledToActiveAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromFrozenToActiveAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesByToActiveAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesToActiveAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromTentativeToActiveAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromCompletedToActiveAction() {
        return (context) -> {/*add some action*/};
    }

    //Actions to Tentative

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromTentativeToTentativeAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromCompletedToTentativeAction() {
        return (context) -> {/*add some action*/};
    }

    //Actions to Completed

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromCompletedToCompletedAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromEnabledToCompletedAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromFrozenToCompletedAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesByToCompletedAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesToCompletedAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromActiveToCompletedAction() {
        return (context) -> {/*add some action*/};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Action<SelfCompletionState, SelfCompletionStateEvents> fromTentativeToCompletedAction() {
        return (context) -> {/*add some action*/};
    }

    //Guards to Disabled

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromNewToDisabledGuard() {
        return (context) -> {/*add some action*/ return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromDisabledToDisabledGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromFrozenToDisabledGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromActiveToDisabledGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromEnabledToDisabledGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromCompletedToDisabledGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesByToDisabledGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesToDisabledGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromTentativeToDisabledGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    //Guards to Enabled

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromNewToEnabledGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromDisabledToEnabledGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromEnabledToEnabledGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromFrozenToEnabledGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesByToEnabledGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesToEnabledGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromActiveToEnabledGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromTentativeToEnabledGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromCompletedToEnabledGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    //Guards to Frozen

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromFrozenToFrozenGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromEnabledToFrozenGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesByToFrozenGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesToFrozenGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromActiveToFrozenGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromTentativeToFrozenGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromCompletedToFrozenGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    //Guards to FrozenWithForciblyUnlockedRelativesBy

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesByToFrozenWithForciblyUnlockedRelativesByGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromEnabledToFrozenWithForciblyUnlockedRelativesByGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenToFrozenWithForciblyUnlockedRelativesByGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesToFrozenWithForciblyUnlockedRelativesByGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromActiveToFrozenWithForciblyUnlockedRelativesByGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromTentativeToFrozenWithForciblyUnlockedRelativesByGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromCompletedToFrozenWithForciblyUnlockedRelativesByGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    //Guards to FrozenWithForciblyUnlockedRelativesBy

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesToFrozenWithForciblyUnlockedRelativesGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromEnabledToFrozenWithForciblyUnlockedRelativesGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenToFrozenWithForciblyUnlockedRelativesGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesByToFrozenWithForciblyUnlockedRelativesGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromActiveToFrozenWithForciblyUnlockedRelativesGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromTentativeToFrozenWithForciblyUnlockedRelativesGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromCompletedToFrozenWithForciblyUnlockedRelativesGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    //Guards to Active

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromActiveToActiveGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromEnabledToActiveGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromFrozenToActiveGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesByToActiveGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesToActiveGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromTentativeToActiveGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromCompletedToActiveGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    //Guards to Tentative

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromTentativeToTentativeGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromCompletedToTentativeGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    //Guards to Completed

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromCompletedToCompletedGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromEnabledToCompletedGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromFrozenToCompletedGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesByToCompletedGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents>
    fromFrozenWithForciblyUnlockedRelativesToCompletedGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromActiveToCompletedGuard() {
        return (context) -> {/*add some action*/return false;};
    }

    /**
     * {@inheritDoc}
     */
    @Bean
    public Guard<SelfCompletionState, SelfCompletionStateEvents> fromTentativeToCompletedGuard() {
        return (context) -> {/*add some action*/return false;};
    }
}