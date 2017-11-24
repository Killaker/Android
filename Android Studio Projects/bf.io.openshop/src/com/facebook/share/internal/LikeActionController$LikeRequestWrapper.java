package com.facebook.share.internal;

private interface LikeRequestWrapper extends RequestWrapper
{
    String getUnlikeToken();
    
    boolean isObjectLiked();
}
