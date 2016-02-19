package com.itbarxproject.utils;

import com.itbarxproject.application.ItbarxGlobal;

/**
 * TODO: Add a class header comment!
 */
public class TextSizeUtil {

    private static class Factor {

        private static float FACTOR_1_2 = 1.2f;
        private static float FACTOR_1_5 = 1f;
        private static float FACTOR_2 = 1.3f;
        private static float FACTOR_3 = 2f;

        public static float getFactor1_2() {
            return FACTOR_1_2;
        }

        public static float getFactor1_5() {
            return FACTOR_1_5;
        }

        public static float getFactor2() {
            return FACTOR_2;
        }

        public static float getFactor3() {
            return FACTOR_3;
        }


    }

    private static float DENSITY = ItbarxGlobal.getDENSITY();


    //COMMON
    private static float EDIT_BOX_TEXT_SIZE = 24f;
    private static float TOOLBAR_TEXT_SIZE = 30f;
    private static float BUTTON_TEXT_SIZE = 24f;
    private static float FRAGMENT_UPPER_BUTTON = 24f;

    //-------------------------------------------------------------------------------------------
    //LIST VIEW ITEM
    private static float LIST_VIEW_REG_ITEM_TEXT_SIZE = 19f;
    private static float LIST_VIEW_BOLD_ITEM_TEXT_SIZE = 19f;

    //-------------------------------------------------------------------------------------------
    //GRID VIEW ITEM
    private static float GRID_VIEW_REG_ITEM_TEXT_SIZE = 19f;
    private static float GRID_VIEW_BOLD_ITEM_TEXT_SIZE = 19f;

    //-------------------------------------------------------------------------------------------
    //LOGIN SD
    private static float LOGIN_OR_TEXT_SIZE = 27f;
    private static float LOGIN_REMEMBER_ME_TEXT_SIZE = 23f;

    //-------------------------------------------------------------------------------------------
    //FORGOT PASSWORD
    private static float FORGOT_CHANGE_PASSWORD_TEXT_SIZE = 23f;
    private static float FORGOT_UPPER_INFO_TEXT_SIZE = 18f;

    //-------------------------------------------------------------------------------------------

    //CREATE NEW USER
    private static float CREATE_ADDPHOTO_TEXT_SIZE = 20f;
    private static float CREATE_MIDDLE_INFO_TEXT_SIZE = 19f;

    //-------------------------------------------------------------------------------------------

    //BARK ACTIVITY
    private static float BARK_SUBTITLE_TEXT_SIZE = 20f;
    private static float BARK_COUNT_TEXT_SIZE = 19f;
    private static float BARK_MINI_BTN_TEXT_SIZE = 19f;


    // -------------------------------------------------------------------------------------------
    //TIMELINE FRAGMENT
    private static float TIMELINE_USERNAME_TEXT_SIZE = 19f;
    private static float TIMELINE_MINS_TEXT_SIZE = 17f;
    private static float TIMELINE_MINI_BUTTON_TEXT_SIZE = 22f;

    //-------------------------------------------------------------------------------------------
    //PROFILE FRAGMENT
    private static float PROFILE_USERNAME_TEXT_SIZE = 20f;
    private static float PROFILE_USER_BIO_TEXT_SIZE = 18f;
    private static float PROFILE_USER_PLACE_TEXT_SIZE = 18f;
    private static float PROFILE_MINI_BUTTON_COUNT_TEXT_SIZE = 17f;
    private static float PROFILE_MINI_BUTTON_TEXT_SIZE = 17f;
    //-------------------------------------------------------------------------------------------
    //RECORD FRAGMENT
    private static float RECORD_START_TEXT_SIZE = 23f;

    //-------------------------------------------------------------------------------------------

    private static float EDIT_PROFILE_HEADING_TEXT_SIZE=23f;
    private static float EDIT_PROFILE_CHANGE_PHOTO_TEXT_SIZE=19f;
    private static float EDIT_PROFILE_NOTIF_DELETE_TEXT_SIZE=24f;


    //COMMON COMPONENTS METHODS
    public static float getEditBoxTextSize() {
        if (DENSITY >= 3.0)
            return (EDIT_BOX_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (EDIT_BOX_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (EDIT_BOX_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (EDIT_BOX_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
            return EDIT_BOX_TEXT_SIZE;
    }

    public static float getToolbarTextSize() {
        if (DENSITY >= 3.0)
            return (TOOLBAR_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (TOOLBAR_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (TOOLBAR_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (TOOLBAR_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
            return TOOLBAR_TEXT_SIZE;

    }

    public static float getButtonTextSize() {
        if (DENSITY >= 3.0)
            return (BUTTON_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (BUTTON_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (BUTTON_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (BUTTON_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
            return BUTTON_TEXT_SIZE;
    }

    public static float getFragBtnTextSize() {
        if (DENSITY >= 3.0)
            return (FRAGMENT_UPPER_BUTTON * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (FRAGMENT_UPPER_BUTTON * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (FRAGMENT_UPPER_BUTTON * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (FRAGMENT_UPPER_BUTTON * Factor.getFactor1_2()) / DENSITY;
        else
            return FRAGMENT_UPPER_BUTTON;
    }

    //-------------------------------------------------------------------------------------------
    //LIST VIEW ITEM TEXTVIEW METHODS
    public static float getListItemRegTextSize() {
        if (DENSITY >= 3.0)
            return (LIST_VIEW_REG_ITEM_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (LIST_VIEW_REG_ITEM_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (LIST_VIEW_REG_ITEM_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (LIST_VIEW_REG_ITEM_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
            return LIST_VIEW_REG_ITEM_TEXT_SIZE;

    }

    public static float getListItemBoldTextSize() {
        if (DENSITY >= 3.0)
            return (LIST_VIEW_BOLD_ITEM_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (LIST_VIEW_BOLD_ITEM_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (LIST_VIEW_BOLD_ITEM_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (LIST_VIEW_BOLD_ITEM_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
            return LIST_VIEW_BOLD_ITEM_TEXT_SIZE;


    }
    //-------------------------------------------------------------------------------------------
    //GRIDVIEW ITEM TEXTVIEW METHODS
    public static float getGridViewRegItemTextSize() {
        if (DENSITY >= 3.0)
            return (GRID_VIEW_REG_ITEM_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (GRID_VIEW_REG_ITEM_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (GRID_VIEW_REG_ITEM_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (GRID_VIEW_REG_ITEM_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
            return GRID_VIEW_REG_ITEM_TEXT_SIZE;

    }

    public static float getGridViewBoldItemTextSize() {
        if (DENSITY >= 3.0)
            return (GRID_VIEW_BOLD_ITEM_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (GRID_VIEW_BOLD_ITEM_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (GRID_VIEW_BOLD_ITEM_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (GRID_VIEW_BOLD_ITEM_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
            return GRID_VIEW_BOLD_ITEM_TEXT_SIZE;


    }
    //-------------------------------------------------------------------------------------------

    // LOGIN COMPONENTS METHODS
    public static float getLoginOrTextSize() {
        if (DENSITY >= 3.0)
            return (LOGIN_OR_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (LOGIN_OR_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (LOGIN_OR_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (LOGIN_OR_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
            return LOGIN_OR_TEXT_SIZE;
    }

    public static float getLoginRememberMeTextSize() {
        if (DENSITY >= 3.0)
            return (LOGIN_REMEMBER_ME_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (LOGIN_REMEMBER_ME_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (LOGIN_REMEMBER_ME_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (LOGIN_REMEMBER_ME_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
            return LOGIN_REMEMBER_ME_TEXT_SIZE;
    }

    //-------------------------------------------------------------------------------------------
    //CREATE NEW USER COMPONENTS METHODS
    public static float getCreateAddPhotoTextSize() {
        if (DENSITY >= 3.0)
            return (CREATE_ADDPHOTO_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (CREATE_ADDPHOTO_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (CREATE_ADDPHOTO_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (CREATE_ADDPHOTO_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
            return CREATE_ADDPHOTO_TEXT_SIZE;
    }

    public static float getCreateMiddleInfoTextSize() {

        if (DENSITY >= 3.0)
            return (CREATE_MIDDLE_INFO_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (CREATE_MIDDLE_INFO_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (CREATE_MIDDLE_INFO_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (CREATE_MIDDLE_INFO_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
        return CREATE_MIDDLE_INFO_TEXT_SIZE;
    }
    //-------------------------------------------------------------------------------------------

    //FORGOT PASSWORD COMPONENTS METHODS
    public static float getForgotChangePasswordTextSize() {
        if (DENSITY >= 3.0)
            return (FORGOT_CHANGE_PASSWORD_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (FORGOT_CHANGE_PASSWORD_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (FORGOT_CHANGE_PASSWORD_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (FORGOT_CHANGE_PASSWORD_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
        return FORGOT_CHANGE_PASSWORD_TEXT_SIZE;
    }

    public static float getForgotUpperInfoTextSize() {
        if (DENSITY >= 3.0)
            return (FORGOT_UPPER_INFO_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (FORGOT_UPPER_INFO_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (FORGOT_UPPER_INFO_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (FORGOT_UPPER_INFO_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
        return FORGOT_UPPER_INFO_TEXT_SIZE;
    }

    //-------------------------------------------------------------------------------------------

    //BARK ACTIVITY COMPONENTS METHODS
    public static float getBarkSubtitleTextSize() {
        if (DENSITY >= 3.0)
            return (BARK_SUBTITLE_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (BARK_SUBTITLE_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (BARK_SUBTITLE_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (BARK_SUBTITLE_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
        return BARK_SUBTITLE_TEXT_SIZE;
    }

    public static float getBarkCountTextSize() {
        if (DENSITY >= 3.0)
            return (BARK_COUNT_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (BARK_COUNT_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (BARK_COUNT_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (BARK_COUNT_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
        return BARK_COUNT_TEXT_SIZE;
    }

    public static float getBarkMiniBtnTextSize() {
        if (DENSITY >= 3.0)
            return (BARK_MINI_BTN_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (BARK_MINI_BTN_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (BARK_MINI_BTN_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (BARK_MINI_BTN_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
        return BARK_MINI_BTN_TEXT_SIZE;
    }


    //-------------------------------------------------------------------------------------------
    //  TIMELINE FRAGMENT METHODS

    public static float getTimelineUsernameTextSize() {
        if (DENSITY >= 3.0)
            return (TIMELINE_USERNAME_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (TIMELINE_USERNAME_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (TIMELINE_USERNAME_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (TIMELINE_USERNAME_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
        return TIMELINE_USERNAME_TEXT_SIZE;
    }

    public static float getTimelineMinsTextSize() {
        if (DENSITY >= 3.0)
            return (TIMELINE_MINS_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (TIMELINE_MINS_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (TIMELINE_MINS_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (TIMELINE_MINS_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
        return TIMELINE_MINS_TEXT_SIZE;

    }

    public static float getTimelineMiniButtonTextSize() {
        if (DENSITY >= 3.0)
            return (TIMELINE_MINI_BUTTON_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (TIMELINE_MINI_BUTTON_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (TIMELINE_MINI_BUTTON_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (TIMELINE_MINI_BUTTON_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
        return TIMELINE_MINI_BUTTON_TEXT_SIZE;


    }

    //-------------------------------------------------------------------------------------------
    //  RECORD FRAGMENT COMPONENTS METHODS
    public static float getRecordStartTextSize() {
        if (DENSITY >= 3.0)
            return (RECORD_START_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (RECORD_START_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (RECORD_START_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (RECORD_START_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else

        return RECORD_START_TEXT_SIZE;
    }

    //-------------------------------------------------------------------------------------------
    //PROFILE FRAGMENT COMPONENTS METHODS
    public static float getProfileUsernameTextSize() {
        if (DENSITY >= 3.0)
            return (PROFILE_USERNAME_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (PROFILE_USERNAME_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (PROFILE_USERNAME_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (PROFILE_USERNAME_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else

        return PROFILE_USERNAME_TEXT_SIZE;
    }

    public static float getProfileUserBioTextSize() {
        if (DENSITY >= 3.0)
            return (PROFILE_USER_BIO_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (PROFILE_USER_BIO_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (PROFILE_USER_BIO_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (PROFILE_USER_BIO_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
        return PROFILE_USER_BIO_TEXT_SIZE;
    }

    public static float getProfileUserPlaceTextSize() {
        if (DENSITY >= 3.0)
            return (PROFILE_USER_PLACE_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (PROFILE_USER_PLACE_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (PROFILE_USER_PLACE_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (PROFILE_USER_PLACE_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
        return PROFILE_USER_PLACE_TEXT_SIZE;
    }

    public static float getProfileMiniButtonCountTextSize() {
        if (DENSITY >= 3.0)
            return (PROFILE_MINI_BUTTON_COUNT_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (PROFILE_MINI_BUTTON_COUNT_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (PROFILE_MINI_BUTTON_COUNT_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (PROFILE_MINI_BUTTON_COUNT_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
        return PROFILE_MINI_BUTTON_COUNT_TEXT_SIZE;
    }

    public static float getProfileMiniButtonTextSize() {
        if (DENSITY >= 3.0)
            return (PROFILE_MINI_BUTTON_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (PROFILE_MINI_BUTTON_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (PROFILE_MINI_BUTTON_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (PROFILE_MINI_BUTTON_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
        return PROFILE_MINI_BUTTON_TEXT_SIZE;
    }

    //-------------------------------------------------------------------------------------------
    //EDIT PROFILE COMPONENTS METHODS
    public static float getEditProfileHeadingTextSize() {
        if (DENSITY >= 3.0)
            return (EDIT_PROFILE_HEADING_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (EDIT_PROFILE_HEADING_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (EDIT_PROFILE_HEADING_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (EDIT_PROFILE_HEADING_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
            return EDIT_PROFILE_HEADING_TEXT_SIZE;
    }
    public static float getEditProfileChangePhotoTextSize() {
        if (DENSITY >= 3.0)
            return (EDIT_PROFILE_CHANGE_PHOTO_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (EDIT_PROFILE_CHANGE_PHOTO_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (EDIT_PROFILE_CHANGE_PHOTO_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (EDIT_PROFILE_CHANGE_PHOTO_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
            return EDIT_PROFILE_CHANGE_PHOTO_TEXT_SIZE;
    }
    public static float getEditProfileNotifDeleteTextSize() {
        if (DENSITY >= 3.0)
            return (EDIT_PROFILE_NOTIF_DELETE_TEXT_SIZE * Factor.getFactor3()) / DENSITY;
        else if (DENSITY >= 2.0)
            return (EDIT_PROFILE_NOTIF_DELETE_TEXT_SIZE * Factor.getFactor2()) / DENSITY;
        else if (DENSITY >= 1.5)
            return (EDIT_PROFILE_NOTIF_DELETE_TEXT_SIZE * Factor.getFactor1_5()) / DENSITY;
        else if (DENSITY >= 1.2)
            return (EDIT_PROFILE_NOTIF_DELETE_TEXT_SIZE * Factor.getFactor1_2()) / DENSITY;
        else
            return EDIT_PROFILE_NOTIF_DELETE_TEXT_SIZE;
    }



}
