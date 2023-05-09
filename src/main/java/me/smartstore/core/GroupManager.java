package me.smartstore.core;

import static me.smartstore.exceptions.StoreErrorCode.*;
import static me.smartstore.enums.GroupType.*;
import static me.smartstore.menus.ParameterSubMenu.inputParameter;

import java.util.Arrays;

import me.smartstore.domain.Group;
import me.smartstore.domain.Parameter;
import me.smartstore.exceptions.StoreErrorCode;
import me.smartstore.exceptions.StoreException;
import me.smartstore.enums.GroupType;
import me.smartstore.utils.ScannerHolder;

public class GroupManager {
  private static final Group[] groups =
      new Group[] {
        new Group(NONE, null), new Group(GENERAL, null), new Group(VIP, null), new Group(VVIP, null)
      };

  public static void launchGroupManager(int menuNumber) {
    while (true) {
      printGroupTypeInputMessage();

      try {
        String input = ScannerHolder.getInput().toUpperCase();

        if ("end".equalsIgnoreCase(input)) return;
        if ("".equals(input)) throw new StoreException(NULL_INPUT);

        GroupType targetType = convertInputStrToGroupType(input);
        switch (menuNumber) {
          case 1 -> setGroupParameter(targetType);
          case 2 -> viewGroupParameter(targetType);
          case 3 -> updateGroupParameter(targetType);
        }
      } catch (StoreException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private static void printGroupTypeInputMessage() {
    System.out.println(
        "Which group (GENERAL (G), VIP (V), VVIP (VV))?\n"
            + "** Press 'end', if you want to exit! **");
  }

  private static GroupType convertInputStrToGroupType(String input) throws StoreException {
    try {
      return GroupType.valueOf(input).replaceFullName();
    } catch (IllegalArgumentException e) {
      throw new StoreException(StoreErrorCode.INVALID_INPUT);
    }
  }

  private static void setGroupParameter(GroupType targetType) throws StoreException {
    Group targetGroup = getTargetGroup(targetType);

    if (targetGroup.getParameter() != null) {
      throw new StoreException(GROUP_ALREADY_SET);
    }

    Parameter newParameter = inputParameter();
    targetGroup.setParameter(newParameter);
  }

  private static void updateGroupParameter(GroupType targetType) throws StoreException {
    Group targetGroup = getTargetGroup(targetType);

    if (targetGroup.getParameter() == null) {
      throw new StoreException(NO_PARAMETER);
    }

    Parameter inputParams = inputParameter();

    targetGroup.setParameter(inputParams);
  }

  private static void viewGroupParameter(GroupType targetType) throws StoreException {
    Group targetGroup = getTargetGroup(targetType);
    System.out.println(targetGroup);
  }

  private static Group getTargetGroup(GroupType targetType) throws StoreException {
    return Arrays.stream(groups)
        .filter(group -> group.getGroupType() == targetType)
        .findFirst()
        .orElseThrow(() -> new StoreException(NO_GROUP));
  }
}
