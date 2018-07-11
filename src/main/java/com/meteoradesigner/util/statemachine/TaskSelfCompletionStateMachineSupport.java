package com.meteoradesigner.util.statemachine;

import com.meteoradesigner.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//TODO
@Service
public class TaskSelfCompletionStateMachineSupport {
    @Autowired
    private TaskService taskService;

    public void switchFromNonBlockedToFreezedOrFreezedWithForciblyUnlockedRelativesBy(){

    }

    public void switchFromFreezedOrFreezedWithForciblyUnlockedRelativesByToNonBlocked(){

    }

    public void switchFromNonBlockedToFreezedWithForciblyUnlockedRelatives(){

    }

    public void switchFromFreezedWithForciblyUnlockedRelativesToNonBlocked(){

    }

    public void switchFromFreezedWithForciblyUnlockedRelativesToOtherBlocked(){

    }

    public void switchFreezedOrFreezedWithForciblyUnlockedRelativesByToFreezedWithForciblyUnlockedRelatives(){

    }

    public void updateRelativesOfBlockedUpCommon(){

    }

    public void updateRelativesOfUnBlockedUpCommon(){

    }

    public void updateRelativesOfBlockedDownCommon(){

    }

    public void updateRelativesOfUnBlockedDownCommon(){

    }
}