package okhttp3;

import okio.*;

static final class ResponseBody$1 extends ResponseBody {
    final /* synthetic */ BufferedSource val$content;
    final /* synthetic */ long val$contentLength;
    final /* synthetic */ MediaType val$contentType;
    
    @Override
    public long contentLength() {
        return this.val$contentLength;
    }
    
    @Override
    public MediaType contentType() {
        return this.val$contentType;
    }
    
    @Override
    public BufferedSource source() {
        return this.val$content;
    }
}