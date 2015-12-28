package io.teamkona.konatools.network.services;

/**
 * Created by gonzalomelov on 9/1/15.
 **/
public class Constants {
  public static final String OFFSET = "offset";
  public static final String LIMIT = "limit";
  public static final String WHERE = "where";
  public static final String IN = "[$in]";
  public static final String GREATER_THAN = "[$gt]";
  public static final String LOWER_THAN = "[$lt]";

  public static final String SORT = "sort";
  public static final int SORT_ASCENDING = 1;
  public static final int SORT_DESCENDING = 1;
  public static final String SORT_ID = Constants.SORT + "[" + Constants.ID + "]";

  public static final String ID = "_id";
  public static final String CREATED_AT = "_createdAt";
  public static final String UPDATED_AT = "_updatedAt";
}
