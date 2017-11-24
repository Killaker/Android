package android.support.v4.text;

private static class DirectionalityEstimator
{
    private static final byte[] DIR_TYPE_CACHE;
    private static final int DIR_TYPE_CACHE_SIZE = 1792;
    private int charIndex;
    private final boolean isHtml;
    private char lastChar;
    private final int length;
    private final String text;
    
    static {
        DIR_TYPE_CACHE = new byte[1792];
        for (int i = 0; i < 1792; ++i) {
            DirectionalityEstimator.DIR_TYPE_CACHE[i] = Character.getDirectionality(i);
        }
    }
    
    DirectionalityEstimator(final String text, final boolean isHtml) {
        this.text = text;
        this.isHtml = isHtml;
        this.length = text.length();
    }
    
    private static byte getCachedDirectionality(final char c) {
        if (c < '\u0700') {
            return DirectionalityEstimator.DIR_TYPE_CACHE[c];
        }
        return Character.getDirectionality(c);
    }
    
    private byte skipEntityBackward() {
        final int charIndex = this.charIndex;
        while (this.charIndex > 0) {
            final String text = this.text;
            final int charIndex2 = -1 + this.charIndex;
            this.charIndex = charIndex2;
            this.lastChar = text.charAt(charIndex2);
            if (this.lastChar == '&') {
                return 12;
            }
            if (this.lastChar == ';') {
                break;
            }
        }
        this.charIndex = charIndex;
        this.lastChar = ';';
        return 13;
    }
    
    private byte skipEntityForward() {
        while (this.charIndex < this.length && (this.lastChar = this.text.charAt(this.charIndex++)) != ';') {}
        return 12;
    }
    
    private byte skipTagBackward() {
        final int charIndex = this.charIndex;
        while (this.charIndex > 0) {
            final String text = this.text;
            final int charIndex2 = -1 + this.charIndex;
            this.charIndex = charIndex2;
            this.lastChar = text.charAt(charIndex2);
            if (this.lastChar == '<') {
                return 12;
            }
            if (this.lastChar == '>') {
                break;
            }
            if (this.lastChar != '\"' && this.lastChar != '\'') {
                continue;
            }
            final char lastChar = this.lastChar;
            while (this.charIndex > 0) {
                final String text2 = this.text;
                final int charIndex3 = -1 + this.charIndex;
                this.charIndex = charIndex3;
                if ((this.lastChar = text2.charAt(charIndex3)) != lastChar) {
                    continue;
                }
                break;
            }
        }
        this.charIndex = charIndex;
        this.lastChar = '>';
        return 13;
    }
    
    private byte skipTagForward() {
        final int charIndex = this.charIndex;
        while (this.charIndex < this.length) {
            this.lastChar = this.text.charAt(this.charIndex++);
            if (this.lastChar == '>') {
                return 12;
            }
            if (this.lastChar != '\"' && this.lastChar != '\'') {
                continue;
            }
            final char lastChar = this.lastChar;
            while (this.charIndex < this.length && (this.lastChar = this.text.charAt(this.charIndex++)) != lastChar) {}
        }
        this.charIndex = charIndex;
        this.lastChar = '<';
        return 13;
    }
    
    byte dirTypeBackward() {
        this.lastChar = this.text.charAt(-1 + this.charIndex);
        byte b;
        if (Character.isLowSurrogate(this.lastChar)) {
            final int codePointBefore = Character.codePointBefore(this.text, this.charIndex);
            this.charIndex -= Character.charCount(codePointBefore);
            b = Character.getDirectionality(codePointBefore);
        }
        else {
            --this.charIndex;
            b = getCachedDirectionality(this.lastChar);
            if (this.isHtml) {
                if (this.lastChar == '>') {
                    return this.skipTagBackward();
                }
                if (this.lastChar == ';') {
                    return this.skipEntityBackward();
                }
            }
        }
        return b;
    }
    
    byte dirTypeForward() {
        this.lastChar = this.text.charAt(this.charIndex);
        byte b;
        if (Character.isHighSurrogate(this.lastChar)) {
            final int codePoint = Character.codePointAt(this.text, this.charIndex);
            this.charIndex += Character.charCount(codePoint);
            b = Character.getDirectionality(codePoint);
        }
        else {
            ++this.charIndex;
            b = getCachedDirectionality(this.lastChar);
            if (this.isHtml) {
                if (this.lastChar == '<') {
                    return this.skipTagForward();
                }
                if (this.lastChar == '&') {
                    return this.skipEntityForward();
                }
            }
        }
        return b;
    }
    
    int getEntryDir() {
        this.charIndex = 0;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        while (this.charIndex < this.length && n3 == 0) {
            switch (this.dirTypeForward()) {
                case 18: {
                    --n;
                    n2 = 0;
                    continue;
                }
                case 16:
                case 17: {
                    ++n;
                    n2 = 1;
                    continue;
                }
                case 14:
                case 15: {
                    ++n;
                    n2 = -1;
                }
                case 9: {
                    continue;
                }
                default: {
                    n3 = n;
                    continue;
                }
                case 0: {
                    if (n == 0) {
                        n2 = -1;
                        return n2;
                    }
                    n3 = n;
                    continue;
                }
                case 1:
                case 2: {
                    if (n == 0) {
                        return 1;
                    }
                    n3 = n;
                    continue;
                }
            }
        }
        if (n3 == 0) {
            return 0;
        }
        if (n2 == 0) {
            while (this.charIndex > 0) {
                switch (this.dirTypeBackward()) {
                    default: {
                        continue;
                    }
                    case 14:
                    case 15: {
                        if (n3 == n) {
                            return -1;
                        }
                        --n;
                        continue;
                    }
                    case 16:
                    case 17: {
                        if (n3 == n) {
                            return 1;
                        }
                        --n;
                        continue;
                    }
                    case 18: {
                        ++n;
                        continue;
                    }
                }
            }
            return 0;
        }
        return n2;
    }
    
    int getExitDir() {
        this.charIndex = this.length;
        int n = 0;
        int n2 = 0;
        while (this.charIndex > 0) {
            switch (this.dirTypeBackward()) {
                case 18: {
                    ++n;
                }
                case 9: {
                    continue;
                }
                default: {
                    if (n2 == 0) {
                        n2 = n;
                        continue;
                    }
                    continue;
                }
                case 0: {
                    if (n == 0) {
                        break;
                    }
                    if (n2 == 0) {
                        n2 = n;
                        continue;
                    }
                    continue;
                }
                case 14:
                case 15: {
                    if (n2 != n) {
                        --n;
                        continue;
                    }
                    break;
                }
                case 1:
                case 2: {
                    if (n == 0) {
                        return 1;
                    }
                    if (n2 == 0) {
                        n2 = n;
                        continue;
                    }
                    continue;
                }
                case 16:
                case 17: {
                    if (n2 == n) {
                        return 1;
                    }
                    --n;
                    continue;
                }
            }
            return -1;
        }
        return 0;
    }
}
