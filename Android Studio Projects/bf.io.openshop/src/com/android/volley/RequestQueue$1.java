package com.android.volley;

class RequestQueue$1 implements RequestFilter {
    final /* synthetic */ Object val$tag;
    
    @Override
    public boolean apply(final Request<?> request) {
        return request.getTag() == this.val$tag;
    }
}