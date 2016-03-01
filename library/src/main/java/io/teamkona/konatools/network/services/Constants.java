package io.teamkona.konatools.network.services;

/**
 * Created by gonzalomelov on 9/1/15.
 **/
public class Constants {
  public static final String ID = "_id";
  public static final String CREATED_AT = "_createdAt";
  public static final String UPDATED_AT = "_updatedAt";

  public static final String START_DATE = "startDate";
  public static final String END_DATE = "endDate";
  public static final String GENDER = "gender";
  public static final String BIRTHDAY = "birthday";
  public static final String HOW_TRAVEL = "howTravel";
  public static final String NAME = "name";

  public static final String HEADER_AUTHORIZATION = "Authorization";
  public static final String HEADER_AUTHORIZATION_BEARER = "Bearer";
  public static final String HEADER_FACEBOOK_ACCESS_TOKEN = "X-FACEBOOK-ACCESS-TOKEN";
  public static final String HEADER_MAX_DISTANCE = "X-MAX-DISTANCE";
  public static final String HEADER_MIN_DISTANCE = "X-MIN-DISTANCE";
  public static final String HEADER_API_KEY = "X-API-KEY";
  public static final String HEADER_CURRENT_POSITION = "X-CURRENT-POSITION";
  public static final String HEADER_CURRENT_HOSTEL = "X-CURRENT-HOSTEL";
  public static final String HEADER_USER_LANGUAGE = "X-USER-LANGUAGE";
  public static final String HEADER_TIMEZONE = "X-TIMEZONE";

  public static final String OFFSET = "offset";
  public static final String LIMIT = "limit";
  public static final String WHERE = "where";
  public static final String CASE_INSENSITIVE = "i";
  public static final String IN = "[$in]";
  public static final String GREATER_THAN = "[$gt]";
  public static final String LOWER_THAN = "[$lt]";
  public static final String OPTIONS = "[$options]";
  public static final String REGEX = "[$regex]";
  public static final String UNSET = "$unset";
  public static final String POPULATE = "populate[]";

  public static final String SORT = "sort";
  public static final int SORT_ASCENDING = 1;
  public static final int SORT_DESCENDING = -1;

  public static final String SORT_ID = SORT + "[" + ID + "]";
  public static final String SORT_UPDATED_AT = SORT + "[" + UPDATED_AT + "]";
  public static final String SORT_START_DATE = SORT + "[" + START_DATE + "]";
  public static final String SORT_END_DATE = SORT + "[" + END_DATE + "]";

  public static final String WHERE_MIN_START_DATE = WHERE + "[" + START_DATE + "]" + GREATER_THAN;
  public static final String WHERE_MAX_END_DATE = WHERE + "[" + END_DATE + "]" + LOWER_THAN;
  public static final String WHERE_MAX_START_DATE = WHERE + "[" + START_DATE + "]" + LOWER_THAN;
  public static final String WHERE_MIN_BIRTHDAY = WHERE + "[" + BIRTHDAY + "]" + GREATER_THAN;
  public static final String WHERE_MAX_BIRTHDAY = WHERE + "[" + BIRTHDAY + "]" + LOWER_THAN;
  public static final String WHERE_GENDER = WHERE + "[" + GENDER + "]" + IN;
  public static final String WHERE_HOW_TRAVEL = WHERE + "[" + HOW_TRAVEL + "]" + IN;
  public static final String WHERE_NAME_OPTIONS = Constants.WHERE + "[" + NAME + "]" + Constants.OPTIONS;
  public static final String WHERE_NAME_REGEX = Constants.WHERE + "[" + NAME + "]" + Constants.REGEX;
}

