package com.squareup.picasso;

public interface RequestTransformer
{
    public static final RequestTransformer IDENTITY = new RequestTransformer() {
        @Override
        public Request transformRequest(final Request request) {
            return request;
        }
    };
    
    Request transformRequest(final Request p0);
}
