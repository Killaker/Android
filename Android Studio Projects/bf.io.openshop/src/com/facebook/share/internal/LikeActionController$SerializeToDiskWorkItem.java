package com.facebook.share.internal;

private static class SerializeToDiskWorkItem implements Runnable
{
    private String cacheKey;
    private String controllerJson;
    
    SerializeToDiskWorkItem(final String cacheKey, final String controllerJson) {
        this.cacheKey = cacheKey;
        this.controllerJson = controllerJson;
    }
    
    @Override
    public void run() {
        LikeActionController.access$2600(this.cacheKey, this.controllerJson);
    }
}
