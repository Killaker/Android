package com.google.gson.stream;

import com.google.gson.internal.*;
import com.google.gson.internal.bind.*;
import java.io.*;

public class JsonReader implements Closeable
{
    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final char[] NON_EXECUTE_PREFIX;
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 17;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 15;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 16;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private final char[] buffer;
    private final Reader in;
    private boolean lenient;
    private int limit;
    private int lineNumber;
    private int lineStart;
    private int[] pathIndices;
    private String[] pathNames;
    private int peeked;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private int pos;
    private int[] stack;
    private int stackSize;
    
    static {
        NON_EXECUTE_PREFIX = ")]}'\n".toCharArray();
        JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess() {
            @Override
            public void promoteNameToValue(final JsonReader jsonReader) throws IOException {
                if (jsonReader instanceof JsonTreeReader) {
                    ((JsonTreeReader)jsonReader).promoteNameToValue();
                    return;
                }
                int n = jsonReader.peeked;
                if (n == 0) {
                    n = jsonReader.doPeek();
                }
                if (n == 13) {
                    jsonReader.peeked = 9;
                    return;
                }
                if (n == 12) {
                    jsonReader.peeked = 8;
                    return;
                }
                if (n == 14) {
                    jsonReader.peeked = 10;
                    return;
                }
                throw new IllegalStateException("Expected a name but was " + jsonReader.peek() + " " + " at line " + jsonReader.getLineNumber() + " column " + jsonReader.getColumnNumber() + " path " + jsonReader.getPath());
            }
        };
    }
    
    public JsonReader(final Reader in) {
        this.lenient = false;
        this.buffer = new char[1024];
        this.pos = 0;
        this.limit = 0;
        this.lineNumber = 0;
        this.lineStart = 0;
        this.peeked = 0;
        this.stack = new int[32];
        this.stackSize = 0;
        this.stack[this.stackSize++] = 6;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        if (in == null) {
            throw new NullPointerException("in == null");
        }
        this.in = in;
    }
    
    private void checkLenient() throws IOException {
        if (!this.lenient) {
            throw this.syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }
    
    private void consumeNonExecutePrefix() throws IOException {
        this.nextNonWhitespace(true);
        --this.pos;
        if (this.pos + JsonReader.NON_EXECUTE_PREFIX.length <= this.limit || this.fillBuffer(JsonReader.NON_EXECUTE_PREFIX.length)) {
            for (int i = 0; i < JsonReader.NON_EXECUTE_PREFIX.length; ++i) {
                if (this.buffer[i + this.pos] != JsonReader.NON_EXECUTE_PREFIX[i]) {
                    return;
                }
            }
            this.pos += JsonReader.NON_EXECUTE_PREFIX.length;
        }
    }
    
    private int doPeek() throws IOException {
        final int n = this.stack[-1 + this.stackSize];
        if (n == 1) {
            this.stack[-1 + this.stackSize] = 2;
        }
        else if (n == 2) {
            switch (this.nextNonWhitespace(true)) {
                case 59: {
                    this.checkLenient();
                }
                case 44: {
                    break;
                }
                default: {
                    throw this.syntaxError("Unterminated array");
                }
                case 93: {
                    return this.peeked = 4;
                }
            }
        }
        else if (n == 3 || n == 5) {
            this.stack[-1 + this.stackSize] = 4;
            if (n == 5) {
                switch (this.nextNonWhitespace(true)) {
                    default: {
                        throw this.syntaxError("Unterminated object");
                    }
                    case 125: {
                        return this.peeked = 2;
                    }
                    case 59: {
                        this.checkLenient();
                    }
                    case 44: {
                        break;
                    }
                }
            }
            final int nextNonWhitespace = this.nextNonWhitespace(true);
            switch (nextNonWhitespace) {
                default: {
                    this.checkLenient();
                    --this.pos;
                    if (this.isLiteral((char)nextNonWhitespace)) {
                        return this.peeked = 14;
                    }
                    throw this.syntaxError("Expected name");
                }
                case 34: {
                    return this.peeked = 13;
                }
                case 39: {
                    this.checkLenient();
                    return this.peeked = 12;
                }
                case 125: {
                    if (n != 5) {
                        return this.peeked = 2;
                    }
                    throw this.syntaxError("Expected name");
                }
            }
        }
        else if (n == 4) {
            this.stack[-1 + this.stackSize] = 5;
            switch (this.nextNonWhitespace(true)) {
                case 58: {
                    break;
                }
                default: {
                    throw this.syntaxError("Expected ':'");
                }
                case 61: {
                    this.checkLenient();
                    if ((this.pos < this.limit || this.fillBuffer(1)) && this.buffer[this.pos] == '>') {
                        ++this.pos;
                        break;
                    }
                    break;
                }
            }
        }
        else if (n == 6) {
            if (this.lenient) {
                this.consumeNonExecutePrefix();
            }
            this.stack[-1 + this.stackSize] = 7;
        }
        else if (n == 7) {
            if (this.nextNonWhitespace(false) == -1) {
                return this.peeked = 17;
            }
            this.checkLenient();
            --this.pos;
        }
        else if (n == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        switch (this.nextNonWhitespace(true)) {
            default: {
                --this.pos;
                if (this.stackSize == 1) {
                    this.checkLenient();
                }
                int n2 = this.peekKeyword();
                if (n2 == 0) {
                    n2 = this.peekNumber();
                    if (n2 == 0) {
                        if (!this.isLiteral(this.buffer[this.pos])) {
                            throw this.syntaxError("Expected value");
                        }
                        this.checkLenient();
                        return this.peeked = 10;
                    }
                }
                return n2;
            }
            case 93: {
                if (n == 1) {
                    return this.peeked = 4;
                }
            }
            case 44:
            case 59: {
                if (n == 1 || n == 2) {
                    this.checkLenient();
                    --this.pos;
                    return this.peeked = 7;
                }
                throw this.syntaxError("Unexpected value");
            }
            case 39: {
                this.checkLenient();
                return this.peeked = 8;
            }
            case 34: {
                if (this.stackSize == 1) {
                    this.checkLenient();
                }
                return this.peeked = 9;
            }
            case 91: {
                return this.peeked = 3;
            }
            case 123: {
                return this.peeked = 1;
            }
        }
    }
    
    private boolean fillBuffer(int n) throws IOException {
        final char[] buffer = this.buffer;
        this.lineStart -= this.pos;
        if (this.limit != this.pos) {
            this.limit -= this.pos;
            System.arraycopy(buffer, this.pos, buffer, 0, this.limit);
        }
        else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            final int read = this.in.read(buffer, this.limit, buffer.length - this.limit);
            final boolean b = false;
            if (read == -1) {
                return b;
            }
            this.limit += read;
            if (this.lineNumber != 0 || this.lineStart != 0 || this.limit <= 0 || buffer[0] != '\ufeff') {
                continue;
            }
            ++this.pos;
            ++this.lineStart;
            ++n;
        } while (this.limit < n);
        return true;
    }
    
    private int getColumnNumber() {
        return 1 + (this.pos - this.lineStart);
    }
    
    private int getLineNumber() {
        return 1 + this.lineNumber;
    }
    
    private boolean isLiteral(final char c) throws IOException {
        switch (c) {
            default: {
                return true;
            }
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\': {
                this.checkLenient();
            }
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case '{':
            case '}': {
                return false;
            }
        }
    }
    
    private int nextNonWhitespace(final boolean b) throws IOException {
        final char[] buffer = this.buffer;
        int pos = this.pos;
        int n = this.limit;
        while (true) {
            if (pos == n) {
                this.pos = pos;
                if (!this.fillBuffer(1)) {
                    if (b) {
                        throw new EOFException("End of input at line " + this.getLineNumber() + " column " + this.getColumnNumber());
                    }
                    return -1;
                }
                else {
                    pos = this.pos;
                    n = this.limit;
                }
            }
            final int n2 = pos + 1;
            final char c = buffer[pos];
            if (c == '\n') {
                ++this.lineNumber;
                this.lineStart = n2;
                pos = n2;
            }
            else if (c != ' ' && c != '\r') {
                if (c == '\t') {
                    pos = n2;
                }
                else if (c == '/') {
                    if ((this.pos = n2) == n) {
                        --this.pos;
                        final boolean fillBuffer = this.fillBuffer(2);
                        ++this.pos;
                        if (!fillBuffer) {
                            return c;
                        }
                    }
                    this.checkLenient();
                    switch (buffer[this.pos]) {
                        default: {
                            return c;
                        }
                        case '*': {
                            ++this.pos;
                            if (!this.skipTo("*/")) {
                                throw this.syntaxError("Unterminated comment");
                            }
                            pos = 2 + this.pos;
                            n = this.limit;
                            continue;
                        }
                        case '/': {
                            ++this.pos;
                            this.skipToEndOfLine();
                            pos = this.pos;
                            n = this.limit;
                            continue;
                        }
                    }
                }
                else {
                    if (c != '#') {
                        this.pos = n2;
                        return c;
                    }
                    this.pos = n2;
                    this.checkLenient();
                    this.skipToEndOfLine();
                    pos = this.pos;
                    n = this.limit;
                }
            }
            else {
                pos = n2;
            }
        }
    }
    
    private String nextQuotedValue(final char c) throws IOException {
        final char[] buffer = this.buffer;
        final StringBuilder sb = new StringBuilder();
        do {
            final int pos = this.pos;
            int n = this.limit;
            int n2 = pos;
            int i;
            int pos2;
            for (i = pos; i < n; i = pos2) {
                pos2 = i + 1;
                final char c2 = buffer[i];
                if (c2 == c) {
                    sb.append(buffer, n2, -1 + ((this.pos = pos2) - n2));
                    return sb.toString();
                }
                if (c2 == '\\') {
                    sb.append(buffer, n2, -1 + ((this.pos = pos2) - n2));
                    sb.append(this.readEscapeCharacter());
                    pos2 = this.pos;
                    n = this.limit;
                    n2 = pos2;
                }
                else if (c2 == '\n') {
                    ++this.lineNumber;
                    this.lineStart = pos2;
                }
            }
            sb.append(buffer, n2, i - n2);
            this.pos = i;
        } while (this.fillBuffer(1));
        throw this.syntaxError("Unterminated string");
    }
    
    private String nextUnquotedValue() throws IOException {
        StringBuilder sb = null;
        int n = 0;
    Label_0178:
        while (true) {
            Block_6: {
                while (true) {
                    if (n + this.pos < this.limit) {
                        switch (this.buffer[n + this.pos]) {
                            default: {
                                ++n;
                                continue;
                            }
                            case '#':
                            case '/':
                            case ';':
                            case '=':
                            case '\\': {
                                this.checkLenient();
                            }
                            case '\t':
                            case '\n':
                            case '\f':
                            case '\r':
                            case ' ':
                            case ',':
                            case ':':
                            case '[':
                            case ']':
                            case '{':
                            case '}': {
                                break Label_0178;
                            }
                        }
                    }
                    else if (n < this.buffer.length) {
                        if (this.fillBuffer(n + 1)) {
                            continue;
                        }
                        break;
                    }
                    else {
                        if (sb == null) {
                            sb = new StringBuilder();
                        }
                        sb.append(this.buffer, this.pos, n);
                        this.pos += n;
                        final boolean fillBuffer = this.fillBuffer(1);
                        n = 0;
                        if (!fillBuffer) {
                            break Block_6;
                        }
                        continue;
                    }
                }
                String string;
                if (sb == null) {
                    string = new String(this.buffer, this.pos, n);
                }
                else {
                    sb.append(this.buffer, this.pos, n);
                    string = sb.toString();
                }
                this.pos += n;
                return string;
            }
            n = 0;
            continue Label_0178;
        }
    }
    
    private int peekKeyword() throws IOException {
        final char c = this.buffer[this.pos];
        String s;
        String s2;
        int peeked;
        if (c == 't' || c == 'T') {
            s = "true";
            s2 = "TRUE";
            peeked = 5;
        }
        else if (c == 'f' || c == 'F') {
            s = "false";
            s2 = "FALSE";
            peeked = 6;
        }
        else {
            if (c != 'n' && c != 'N') {
                return 0;
            }
            s = "null";
            s2 = "NULL";
            peeked = 7;
        }
        final int length = s.length();
        for (int i = 1; i < length; ++i) {
            if (i + this.pos >= this.limit && !this.fillBuffer(i + 1)) {
                return 0;
            }
            final char c2 = this.buffer[i + this.pos];
            if (c2 != s.charAt(i) && c2 != s2.charAt(i)) {
                return 0;
            }
        }
        if ((length + this.pos < this.limit || this.fillBuffer(length + 1)) && this.isLiteral(this.buffer[length + this.pos])) {
            return 0;
        }
        this.pos += length;
        return this.peeked = peeked;
    }
    
    private int peekNumber() throws IOException {
        final char[] buffer = this.buffer;
        int n = this.pos;
        int n2 = this.limit;
        long peekedLong = 0L;
        boolean b = false;
        boolean b2 = true;
        int n3 = 0;
        int peekedNumberLength = 0;
        while (true) {
            char c = '\0';
            Label_0198: {
                while (true) {
                    if (n + peekedNumberLength == n2) {
                        if (peekedNumberLength == buffer.length) {
                            return 0;
                        }
                        if (!this.fillBuffer(peekedNumberLength + 1)) {
                            break;
                        }
                        n = this.pos;
                        n2 = this.limit;
                    }
                    c = buffer[n + peekedNumberLength];
                    switch (c) {
                        default: {
                            if (c < '0' || c > '9') {
                                break Label_0198;
                            }
                            if (n3 == 1 || n3 == 0) {
                                peekedLong = -(c - '0');
                                n3 = 2;
                                break;
                            }
                            if (n3 == 2) {
                                if (peekedLong == 0L) {
                                    return 0;
                                }
                                final long n4 = 10L * peekedLong - (c - '0');
                                boolean b3;
                                if (peekedLong > -922337203685477580L || (peekedLong == -922337203685477580L && n4 < peekedLong)) {
                                    b3 = true;
                                }
                                else {
                                    b3 = false;
                                }
                                b2 &= b3;
                                peekedLong = n4;
                                break;
                            }
                            else {
                                if (n3 == 3) {
                                    n3 = 4;
                                    break;
                                }
                                if (n3 == 5 || n3 == 6) {
                                    n3 = 7;
                                    break;
                                }
                                break;
                            }
                            break;
                        }
                        case 45: {
                            if (n3 == 0) {
                                b = true;
                                n3 = 1;
                                break;
                            }
                            if (n3 == 5) {
                                n3 = 6;
                                break;
                            }
                            return 0;
                        }
                        case 43: {
                            if (n3 == 5) {
                                n3 = 6;
                                break;
                            }
                            return 0;
                        }
                        case 69:
                        case 101: {
                            if (n3 == 2 || n3 == 4) {
                                n3 = 5;
                                break;
                            }
                            return 0;
                        }
                        case 46: {
                            if (n3 == 2) {
                                n3 = 3;
                                break;
                            }
                            return 0;
                        }
                    }
                    ++peekedNumberLength;
                }
                if (n3 == 2 && b2 && (peekedLong != Long.MIN_VALUE || b)) {
                    if (!b) {
                        peekedLong = -peekedLong;
                    }
                    this.peekedLong = peekedLong;
                    this.pos += peekedNumberLength;
                    return this.peeked = 15;
                }
                if (n3 == 2 || n3 == 4 || n3 == 7) {
                    this.peekedNumberLength = peekedNumberLength;
                    return this.peeked = 16;
                }
                return 0;
            }
            if (this.isLiteral(c)) {
                return 0;
            }
            continue;
        }
    }
    
    private void push(final int n) {
        if (this.stackSize == this.stack.length) {
            final int[] stack = new int[2 * this.stackSize];
            final int[] pathIndices = new int[2 * this.stackSize];
            final String[] pathNames = new String[2 * this.stackSize];
            System.arraycopy(this.stack, 0, stack, 0, this.stackSize);
            System.arraycopy(this.pathIndices, 0, pathIndices, 0, this.stackSize);
            System.arraycopy(this.pathNames, 0, pathNames, 0, this.stackSize);
            this.stack = stack;
            this.pathIndices = pathIndices;
            this.pathNames = pathNames;
        }
        this.stack[this.stackSize++] = n;
    }
    
    private char readEscapeCharacter() throws IOException {
        if (this.pos == this.limit && !this.fillBuffer(1)) {
            throw this.syntaxError("Unterminated escape sequence");
        }
        final char c = this.buffer[this.pos++];
        switch (c) {
            case 117: {
                if (4 + this.pos > this.limit && !this.fillBuffer(4)) {
                    throw this.syntaxError("Unterminated escape sequence");
                }
                char c2 = '\0';
                for (int i = this.pos; i < i + 4; ++i) {
                    final char c3 = this.buffer[i];
                    final char c4 = (char)(c2 << 4);
                    if (c3 >= '0' && c3 <= '9') {
                        c2 = (char)(c4 + (c3 - '0'));
                    }
                    else if (c3 >= 'a' && c3 <= 'f') {
                        c2 = (char)(c4 + ('\n' + (c3 - 'a')));
                    }
                    else {
                        if (c3 < 'A' || c3 > 'F') {
                            throw new NumberFormatException("\\u" + new String(this.buffer, this.pos, 4));
                        }
                        c2 = (char)(c4 + ('\n' + (c3 - 'A')));
                    }
                }
                this.pos += 4;
                return c2;
            }
            case 116: {
                return '\t';
            }
            case 98: {
                return '\b';
            }
            case 110: {
                return '\n';
            }
            case 114: {
                return '\r';
            }
            case 102: {
                return '\f';
            }
            case 10: {
                ++this.lineNumber;
                this.lineStart = this.pos;
                break;
            }
        }
        return c;
    }
    
    private void skipQuotedValue(final char c) throws IOException {
        final char[] buffer = this.buffer;
        do {
            final int pos = this.pos;
            int n;
            int i;
            int pos2;
            for (n = this.limit, i = pos; i < n; i = pos2) {
                pos2 = i + 1;
                final char c2 = buffer[i];
                if (c2 == c) {
                    this.pos = pos2;
                    return;
                }
                if (c2 == '\\') {
                    this.pos = pos2;
                    this.readEscapeCharacter();
                    pos2 = this.pos;
                    n = this.limit;
                }
                else if (c2 == '\n') {
                    ++this.lineNumber;
                    this.lineStart = pos2;
                }
            }
            this.pos = i;
        } while (this.fillBuffer(1));
        throw this.syntaxError("Unterminated string");
    }
    
    private boolean skipTo(final String s) throws IOException {
        while (this.pos + s.length() <= this.limit || this.fillBuffer(s.length())) {
            Label_0061: {
                if (this.buffer[this.pos] != '\n') {
                    for (int i = 0; i < s.length(); ++i) {
                        if (this.buffer[i + this.pos] != s.charAt(i)) {
                            break Label_0061;
                        }
                    }
                    return true;
                }
                ++this.lineNumber;
                this.lineStart = 1 + this.pos;
            }
            ++this.pos;
        }
        return false;
    }
    
    private void skipToEndOfLine() throws IOException {
        while (this.pos < this.limit || this.fillBuffer(1)) {
            final char c = this.buffer[this.pos++];
            if (c == '\n') {
                ++this.lineNumber;
                this.lineStart = this.pos;
                break;
            }
            if (c == '\r') {
                return;
            }
        }
    }
    
    private void skipUnquotedValue() throws IOException {
        do {
            int n = 0;
            while (n + this.pos < this.limit) {
                switch (this.buffer[n + this.pos]) {
                    default: {
                        ++n;
                        continue;
                    }
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\': {
                        this.checkLenient();
                    }
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}': {
                        this.pos += n;
                        return;
                    }
                }
            }
            this.pos += n;
        } while (this.fillBuffer(1));
    }
    
    private IOException syntaxError(final String s) throws IOException {
        throw new MalformedJsonException(s + " at line " + this.getLineNumber() + " column " + this.getColumnNumber() + " path " + this.getPath());
    }
    
    public void beginArray() throws IOException {
        int n = this.peeked;
        if (n == 0) {
            n = this.doPeek();
        }
        if (n == 3) {
            this.push(1);
            this.pathIndices[-1 + this.stackSize] = 0;
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_ARRAY but was " + this.peek() + " at line " + this.getLineNumber() + " column " + this.getColumnNumber() + " path " + this.getPath());
    }
    
    public void beginObject() throws IOException {
        int n = this.peeked;
        if (n == 0) {
            n = this.doPeek();
        }
        if (n == 1) {
            this.push(3);
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_OBJECT but was " + this.peek() + " at line " + this.getLineNumber() + " column " + this.getColumnNumber() + " path " + this.getPath());
    }
    
    @Override
    public void close() throws IOException {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.in.close();
    }
    
    public void endArray() throws IOException {
        int n = this.peeked;
        if (n == 0) {
            n = this.doPeek();
        }
        if (n == 4) {
            --this.stackSize;
            final int[] pathIndices = this.pathIndices;
            final int n2 = -1 + this.stackSize;
            ++pathIndices[n2];
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + this.peek() + " at line " + this.getLineNumber() + " column " + this.getColumnNumber() + " path " + this.getPath());
    }
    
    public void endObject() throws IOException {
        int n = this.peeked;
        if (n == 0) {
            n = this.doPeek();
        }
        if (n == 2) {
            --this.stackSize;
            this.pathNames[this.stackSize] = null;
            final int[] pathIndices = this.pathIndices;
            final int n2 = -1 + this.stackSize;
            ++pathIndices[n2];
            this.peeked = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + this.peek() + " at line " + this.getLineNumber() + " column " + this.getColumnNumber() + " path " + this.getPath());
    }
    
    public String getPath() {
        final StringBuilder append = new StringBuilder().append('$');
        for (int i = 0; i < this.stackSize; ++i) {
            switch (this.stack[i]) {
                case 1:
                case 2: {
                    append.append('[').append(this.pathIndices[i]).append(']');
                    break;
                }
                case 3:
                case 4:
                case 5: {
                    append.append('.');
                    if (this.pathNames[i] != null) {
                        append.append(this.pathNames[i]);
                        break;
                    }
                    break;
                }
            }
        }
        return append.toString();
    }
    
    public boolean hasNext() throws IOException {
        int n = this.peeked;
        if (n == 0) {
            n = this.doPeek();
        }
        return n != 2 && n != 4;
    }
    
    public final boolean isLenient() {
        return this.lenient;
    }
    
    public boolean nextBoolean() throws IOException {
        int n = this.peeked;
        if (n == 0) {
            n = this.doPeek();
        }
        if (n == 5) {
            this.peeked = 0;
            final int[] pathIndices = this.pathIndices;
            final int n2 = -1 + this.stackSize;
            ++pathIndices[n2];
            return true;
        }
        if (n == 6) {
            this.peeked = 0;
            final int[] pathIndices2 = this.pathIndices;
            final int n3 = -1 + this.stackSize;
            ++pathIndices2[n3];
            return false;
        }
        throw new IllegalStateException("Expected a boolean but was " + this.peek() + " at line " + this.getLineNumber() + " column " + this.getColumnNumber() + " path " + this.getPath());
    }
    
    public double nextDouble() throws IOException {
        int n = this.peeked;
        if (n == 0) {
            n = this.doPeek();
        }
        if (n == 15) {
            this.peeked = 0;
            final int[] pathIndices = this.pathIndices;
            final int n2 = -1 + this.stackSize;
            ++pathIndices[n2];
            return this.peekedLong;
        }
        if (n == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        }
        else if (n == 8 || n == 9) {
            char c;
            if (n == 8) {
                c = '\'';
            }
            else {
                c = '\"';
            }
            this.peekedString = this.nextQuotedValue(c);
        }
        else if (n == 10) {
            this.peekedString = this.nextUnquotedValue();
        }
        else if (n != 11) {
            throw new IllegalStateException("Expected a double but was " + this.peek() + " at line " + this.getLineNumber() + " column " + this.getColumnNumber() + " path " + this.getPath());
        }
        this.peeked = 11;
        final double double1 = Double.parseDouble(this.peekedString);
        if (!this.lenient && (Double.isNaN(double1) || Double.isInfinite(double1))) {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + double1 + " at line " + this.getLineNumber() + " column " + this.getColumnNumber() + " path " + this.getPath());
        }
        this.peekedString = null;
        this.peeked = 0;
        final int[] pathIndices2 = this.pathIndices;
        final int n3 = -1 + this.stackSize;
        ++pathIndices2[n3];
        return double1;
    }
    
    public int nextInt() throws IOException {
        int n = this.peeked;
        if (n == 0) {
            n = this.doPeek();
        }
        if (n == 15) {
            final int n2 = (int)this.peekedLong;
            if (this.peekedLong != n2) {
                throw new NumberFormatException("Expected an int but was " + this.peekedLong + " at line " + this.getLineNumber() + " column " + this.getColumnNumber() + " path " + this.getPath());
            }
            this.peeked = 0;
            final int[] pathIndices = this.pathIndices;
            final int n3 = -1 + this.stackSize;
            ++pathIndices[n3];
            return n2;
        }
        else {
            while (true) {
                Label_0183: {
                    if (n == 16) {
                        this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
                        this.pos += this.peekedNumberLength;
                        break Label_0183;
                    }
                    Label_0360: {
                        if (n != 8 && n != 9) {
                            break Label_0360;
                        }
                        Label_0354: {
                            if (n != 8) {
                                break Label_0354;
                            }
                            char c = '\'';
                            while (true) {
                                this.peekedString = this.nextQuotedValue(c);
                                try {
                                    final int int1 = Integer.parseInt(this.peekedString);
                                    this.peeked = 0;
                                    final int[] pathIndices2 = this.pathIndices;
                                    final int n4 = -1 + this.stackSize;
                                    ++pathIndices2[n4];
                                    return int1;
                                    throw new IllegalStateException("Expected an int but was " + this.peek() + " at line " + this.getLineNumber() + " column " + this.getColumnNumber() + " path " + this.getPath());
                                    this.peekedString = null;
                                    this.peeked = 0;
                                    final int[] pathIndices3 = this.pathIndices;
                                    final int n5 = -1 + this.stackSize;
                                    ++pathIndices3[n5];
                                    return;
                                    c = '\"';
                                }
                                catch (NumberFormatException ex) {
                                    break;
                                }
                            }
                        }
                    }
                }
                this.peeked = 11;
                final double double1 = Double.parseDouble(this.peekedString);
                final int n6 = (int)double1;
                if (n6 != double1) {
                    throw new NumberFormatException("Expected an int but was " + this.peekedString + " at line " + this.getLineNumber() + " column " + this.getColumnNumber() + " path " + this.getPath());
                }
                continue;
            }
        }
    }
    
    public long nextLong() throws IOException {
        int n = this.peeked;
        if (n == 0) {
            n = this.doPeek();
        }
        if (n == 15) {
            this.peeked = 0;
            final int[] pathIndices = this.pathIndices;
            final int n2 = -1 + this.stackSize;
            ++pathIndices[n2];
            return this.peekedLong;
        }
        Label_0098: {
            if (n == 16) {
                this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
                this.pos += this.peekedNumberLength;
            }
            else {
                if (n == 8 || n == 9) {
                    while (true) {
                        Label_0273: {
                            if (n != 8) {
                                break Label_0273;
                            }
                            final char c = '\'';
                            this.peekedString = this.nextQuotedValue(c);
                            try {
                                final long long1 = Long.parseLong(this.peekedString);
                                this.peeked = 0;
                                final int[] pathIndices2 = this.pathIndices;
                                final int n3 = -1 + this.stackSize;
                                ++pathIndices2[n3];
                                return long1;
                            }
                            catch (NumberFormatException ex) {
                                break Label_0098;
                            }
                        }
                        final char c = '\"';
                        continue;
                    }
                }
                throw new IllegalStateException("Expected a long but was " + this.peek() + " at line " + this.getLineNumber() + " column " + this.getColumnNumber() + " path " + this.getPath());
            }
        }
        this.peeked = 11;
        final double double1 = Double.parseDouble(this.peekedString);
        final long n4 = (long)double1;
        if (n4 != double1) {
            throw new NumberFormatException("Expected a long but was " + this.peekedString + " at line " + this.getLineNumber() + " column " + this.getColumnNumber() + " path " + this.getPath());
        }
        this.peekedString = null;
        this.peeked = 0;
        final int[] pathIndices3 = this.pathIndices;
        final int n5 = -1 + this.stackSize;
        ++pathIndices3[n5];
        return n4;
    }
    
    public String nextName() throws IOException {
        int n = this.peeked;
        if (n == 0) {
            n = this.doPeek();
        }
        String s;
        if (n == 14) {
            s = this.nextUnquotedValue();
        }
        else if (n == 12) {
            s = this.nextQuotedValue('\'');
        }
        else {
            if (n != 13) {
                throw new IllegalStateException("Expected a name but was " + this.peek() + " at line " + this.getLineNumber() + " column " + this.getColumnNumber() + " path " + this.getPath());
            }
            s = this.nextQuotedValue('\"');
        }
        this.peeked = 0;
        return this.pathNames[-1 + this.stackSize] = s;
    }
    
    public void nextNull() throws IOException {
        int n = this.peeked;
        if (n == 0) {
            n = this.doPeek();
        }
        if (n == 7) {
            this.peeked = 0;
            final int[] pathIndices = this.pathIndices;
            final int n2 = -1 + this.stackSize;
            ++pathIndices[n2];
            return;
        }
        throw new IllegalStateException("Expected null but was " + this.peek() + " at line " + this.getLineNumber() + " column " + this.getColumnNumber() + " path " + this.getPath());
    }
    
    public String nextString() throws IOException {
        int n = this.peeked;
        if (n == 0) {
            n = this.doPeek();
        }
        String s;
        if (n == 10) {
            s = this.nextUnquotedValue();
        }
        else if (n == 8) {
            s = this.nextQuotedValue('\'');
        }
        else if (n == 9) {
            s = this.nextQuotedValue('\"');
        }
        else if (n == 11) {
            s = this.peekedString;
            this.peekedString = null;
        }
        else if (n == 15) {
            s = Long.toString(this.peekedLong);
        }
        else {
            if (n != 16) {
                throw new IllegalStateException("Expected a string but was " + this.peek() + " at line " + this.getLineNumber() + " column " + this.getColumnNumber() + " path " + this.getPath());
            }
            s = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        }
        this.peeked = 0;
        final int[] pathIndices = this.pathIndices;
        final int n2 = -1 + this.stackSize;
        ++pathIndices[n2];
        return s;
    }
    
    public JsonToken peek() throws IOException {
        int n = this.peeked;
        if (n == 0) {
            n = this.doPeek();
        }
        switch (n) {
            default: {
                throw new AssertionError();
            }
            case 1: {
                return JsonToken.BEGIN_OBJECT;
            }
            case 2: {
                return JsonToken.END_OBJECT;
            }
            case 3: {
                return JsonToken.BEGIN_ARRAY;
            }
            case 4: {
                return JsonToken.END_ARRAY;
            }
            case 12:
            case 13:
            case 14: {
                return JsonToken.NAME;
            }
            case 5:
            case 6: {
                return JsonToken.BOOLEAN;
            }
            case 7: {
                return JsonToken.NULL;
            }
            case 8:
            case 9:
            case 10:
            case 11: {
                return JsonToken.STRING;
            }
            case 15:
            case 16: {
                return JsonToken.NUMBER;
            }
            case 17: {
                return JsonToken.END_DOCUMENT;
            }
        }
    }
    
    public final void setLenient(final boolean lenient) {
        this.lenient = lenient;
    }
    
    public void skipValue() throws IOException {
        int n = 0;
        do {
            int n2 = this.peeked;
            if (n2 == 0) {
                n2 = this.doPeek();
            }
            if (n2 == 3) {
                this.push(1);
                ++n;
            }
            else if (n2 == 1) {
                this.push(3);
                ++n;
            }
            else if (n2 == 4) {
                --this.stackSize;
                --n;
            }
            else if (n2 == 2) {
                --this.stackSize;
                --n;
            }
            else if (n2 == 14 || n2 == 10) {
                this.skipUnquotedValue();
            }
            else if (n2 == 8 || n2 == 12) {
                this.skipQuotedValue('\'');
            }
            else if (n2 == 9 || n2 == 13) {
                this.skipQuotedValue('\"');
            }
            else if (n2 == 16) {
                this.pos += this.peekedNumberLength;
            }
            this.peeked = 0;
        } while (n != 0);
        final int[] pathIndices = this.pathIndices;
        final int n3 = -1 + this.stackSize;
        ++pathIndices[n3];
        this.pathNames[-1 + this.stackSize] = "null";
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " at line " + this.getLineNumber() + " column " + this.getColumnNumber();
    }
}
