package android.support.graphics.drawable;

import android.graphics.*;
import android.util.*;

public static class PathDataNode
{
    float[] params;
    char type;
    
    private PathDataNode(final char type, final float[] params) {
        this.type = type;
        this.params = params;
    }
    
    private PathDataNode(final PathDataNode pathDataNode) {
        this.type = pathDataNode.type;
        this.params = PathParser.access$300(pathDataNode.params, 0, pathDataNode.params.length);
    }
    
    private static void addCommand(final Path path, final float[] array, char c, final char c2, final float[] array2) {
        int n = 2;
        float n2 = array[0];
        float n3 = array[1];
        float n4 = array[2];
        float n5 = array[3];
        float n6 = array[4];
        float n7 = array[5];
        switch (c2) {
            case 'Z':
            case 'z': {
                path.close();
                n2 = n6;
                n3 = n7;
                n4 = n6;
                n5 = n7;
                path.moveTo(n2, n3);
                break;
            }
            case 'L':
            case 'M':
            case 'T':
            case 'l':
            case 'm':
            case 't': {
                n = 2;
                break;
            }
            case 'H':
            case 'V':
            case 'h':
            case 'v': {
                n = 1;
                break;
            }
            case 'C':
            case 'c': {
                n = 6;
                break;
            }
            case 'Q':
            case 'S':
            case 'q':
            case 's': {
                n = 4;
                break;
            }
            case 'A':
            case 'a': {
                n = 7;
                break;
            }
        }
        for (int i = 0; i < array2.length; i += n) {
            switch (c2) {
                case 'm': {
                    n2 += array2[i + 0];
                    n3 += array2[i + 1];
                    if (i > 0) {
                        path.rLineTo(array2[i + 0], array2[i + 1]);
                        break;
                    }
                    path.rMoveTo(array2[i + 0], array2[i + 1]);
                    n6 = n2;
                    n7 = n3;
                    break;
                }
                case 'M': {
                    n2 = array2[i + 0];
                    n3 = array2[i + 1];
                    if (i > 0) {
                        path.lineTo(array2[i + 0], array2[i + 1]);
                        break;
                    }
                    path.moveTo(array2[i + 0], array2[i + 1]);
                    n6 = n2;
                    n7 = n3;
                    break;
                }
                case 'l': {
                    path.rLineTo(array2[i + 0], array2[i + 1]);
                    n2 += array2[i + 0];
                    n3 += array2[i + 1];
                    break;
                }
                case 'L': {
                    path.lineTo(array2[i + 0], array2[i + 1]);
                    n2 = array2[i + 0];
                    n3 = array2[i + 1];
                    break;
                }
                case 'h': {
                    path.rLineTo(array2[i + 0], 0.0f);
                    n2 += array2[i + 0];
                    break;
                }
                case 'H': {
                    path.lineTo(array2[i + 0], n3);
                    n2 = array2[i + 0];
                    break;
                }
                case 'v': {
                    path.rLineTo(0.0f, array2[i + 0]);
                    n3 += array2[i + 0];
                    break;
                }
                case 'V': {
                    path.lineTo(n2, array2[i + 0]);
                    n3 = array2[i + 0];
                    break;
                }
                case 'c': {
                    path.rCubicTo(array2[i + 0], array2[i + 1], array2[i + 2], array2[i + 3], array2[i + 4], array2[i + 5]);
                    n4 = n2 + array2[i + 2];
                    n5 = n3 + array2[i + 3];
                    n2 += array2[i + 4];
                    n3 += array2[i + 5];
                    break;
                }
                case 'C': {
                    path.cubicTo(array2[i + 0], array2[i + 1], array2[i + 2], array2[i + 3], array2[i + 4], array2[i + 5]);
                    n2 = array2[i + 4];
                    n3 = array2[i + 5];
                    n4 = array2[i + 2];
                    n5 = array2[i + 3];
                    break;
                }
                case 's': {
                    float n8 = 0.0f;
                    float n9 = 0.0f;
                    Label_1025: {
                        if (c != 'c' && c != 's' && c != 'C') {
                            final char c3 = c;
                            n8 = 0.0f;
                            n9 = 0.0f;
                            if (c3 != 'S') {
                                break Label_1025;
                            }
                        }
                        n8 = n2 - n4;
                        n9 = n3 - n5;
                    }
                    path.rCubicTo(n8, n9, array2[i + 0], array2[i + 1], array2[i + 2], array2[i + 3]);
                    n4 = n2 + array2[i + 0];
                    n5 = n3 + array2[i + 1];
                    n2 += array2[i + 2];
                    n3 += array2[i + 3];
                    break;
                }
                case 'S': {
                    float n10 = n2;
                    float n11 = n3;
                    if (c == 'c' || c == 's' || c == 'C' || c == 'S') {
                        n10 = 2.0f * n2 - n4;
                        n11 = 2.0f * n3 - n5;
                    }
                    path.cubicTo(n10, n11, array2[i + 0], array2[i + 1], array2[i + 2], array2[i + 3]);
                    n4 = array2[i + 0];
                    n5 = array2[i + 1];
                    n2 = array2[i + 2];
                    n3 = array2[i + 3];
                    break;
                }
                case 'q': {
                    path.rQuadTo(array2[i + 0], array2[i + 1], array2[i + 2], array2[i + 3]);
                    n4 = n2 + array2[i + 0];
                    n5 = n3 + array2[i + 1];
                    n2 += array2[i + 2];
                    n3 += array2[i + 3];
                    break;
                }
                case 'Q': {
                    path.quadTo(array2[i + 0], array2[i + 1], array2[i + 2], array2[i + 3]);
                    n4 = array2[i + 0];
                    n5 = array2[i + 1];
                    n2 = array2[i + 2];
                    n3 = array2[i + 3];
                    break;
                }
                case 't': {
                    float n12 = 0.0f;
                    float n13 = 0.0f;
                    Label_1439: {
                        if (c != 'q' && c != 't' && c != 'Q') {
                            final char c4 = c;
                            n12 = 0.0f;
                            n13 = 0.0f;
                            if (c4 != 'T') {
                                break Label_1439;
                            }
                        }
                        n12 = n2 - n4;
                        n13 = n3 - n5;
                    }
                    path.rQuadTo(n12, n13, array2[i + 0], array2[i + 1]);
                    n4 = n2 + n12;
                    n5 = n3 + n13;
                    n2 += array2[i + 0];
                    n3 += array2[i + 1];
                    break;
                }
                case 'T': {
                    float n14 = n2;
                    float n15 = n3;
                    if (c == 'q' || c == 't' || c == 'Q' || c == 'T') {
                        n14 = 2.0f * n2 - n4;
                        n15 = 2.0f * n3 - n5;
                    }
                    path.quadTo(n14, n15, array2[i + 0], array2[i + 1]);
                    n4 = n14;
                    n5 = n15;
                    n2 = array2[i + 0];
                    n3 = array2[i + 1];
                    break;
                }
                case 'a': {
                    drawArc(path, n2, n3, n2 + array2[i + 5], n3 + array2[i + 6], array2[i + 0], array2[i + 1], array2[i + 2], array2[i + 3] != 0.0f, array2[i + 4] != 0.0f);
                    n2 += array2[i + 5];
                    n3 += array2[i + 6];
                    n4 = n2;
                    n5 = n3;
                    break;
                }
                case 'A': {
                    drawArc(path, n2, n3, array2[i + 5], array2[i + 6], array2[i + 0], array2[i + 1], array2[i + 2], array2[i + 3] != 0.0f, array2[i + 4] != 0.0f);
                    n2 = array2[i + 5];
                    n3 = array2[i + 6];
                    n4 = n2;
                    n5 = n3;
                    break;
                }
            }
            c = c2;
        }
        array[0] = n2;
        array[1] = n3;
        array[2] = n4;
        array[3] = n5;
        array[4] = n6;
        array[5] = n7;
    }
    
    private static void arcToBezier(final Path path, final double n, final double n2, final double n3, final double n4, double n5, double n6, final double n7, final double n8, final double n9) {
        final int n10 = (int)Math.ceil(Math.abs(4.0 * n9 / 3.141592653589793));
        double n11 = n8;
        final double cos = Math.cos(n7);
        final double sin = Math.sin(n7);
        final double cos2 = Math.cos(n11);
        final double sin2 = Math.sin(n11);
        double n12 = sin2 * (cos * -n3) - cos2 * (n4 * sin);
        double n13 = sin2 * (sin * -n3) + cos2 * (n4 * cos);
        final double n14 = n9 / n10;
        for (int i = 0; i < n10; ++i) {
            final double n15 = n11 + n14;
            final double sin3 = Math.sin(n15);
            final double cos3 = Math.cos(n15);
            final double n16 = n + cos3 * (n3 * cos) - sin3 * (n4 * sin);
            final double n17 = n2 + cos3 * (n3 * sin) + sin3 * (n4 * cos);
            final double n18 = sin3 * (cos * -n3) - cos3 * (n4 * sin);
            final double n19 = sin3 * (sin * -n3) + cos3 * (n4 * cos);
            final double tan = Math.tan((n15 - n11) / 2.0);
            final double n20 = Math.sin(n15 - n11) * (Math.sqrt(4.0 + tan * (3.0 * tan)) - 1.0) / 3.0;
            path.cubicTo((float)(n5 + n20 * n12), (float)(n6 + n20 * n13), (float)(n16 - n20 * n18), (float)(n17 - n20 * n19), (float)n16, (float)n17);
            n11 = n15;
            n5 = n16;
            n6 = n17;
            n12 = n18;
            n13 = n19;
        }
    }
    
    private static void drawArc(final Path path, final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final boolean b, final boolean b2) {
        final double radians = Math.toRadians(n7);
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        final double n8 = (cos * n + sin * n2) / n5;
        final double n9 = (sin * -n + cos * n2) / n6;
        final double n10 = (cos * n3 + sin * n4) / n5;
        final double n11 = (sin * -n3 + cos * n4) / n6;
        final double n12 = n8 - n10;
        final double n13 = n9 - n11;
        final double n14 = (n8 + n10) / 2.0;
        final double n15 = (n9 + n11) / 2.0;
        final double n16 = n12 * n12 + n13 * n13;
        if (n16 == 0.0) {
            Log.w("PathParser", " Points are coincident");
            return;
        }
        final double n17 = 1.0 / n16 - 0.25;
        if (n17 < 0.0) {
            Log.w("PathParser", "Points are too far apart " + n16);
            final float n18 = (float)(Math.sqrt(n16) / 1.99999);
            drawArc(path, n, n2, n3, n4, n5 * n18, n6 * n18, n7, b, b2);
            return;
        }
        final double sqrt = Math.sqrt(n17);
        final double n19 = sqrt * n12;
        final double n20 = sqrt * n13;
        double n21;
        double n22;
        if (b == b2) {
            n21 = n14 - n20;
            n22 = n15 + n19;
        }
        else {
            n21 = n14 + n20;
            n22 = n15 - n19;
        }
        final double atan2 = Math.atan2(n9 - n22, n8 - n21);
        double n23 = Math.atan2(n11 - n22, n10 - n21) - atan2;
        if (b2 != n23 >= 0.0) {
            if (n23 > 0.0) {
                n23 -= 6.283185307179586;
            }
            else {
                n23 += 6.283185307179586;
            }
        }
        final double n24 = n21 * n5;
        final double n25 = n22 * n6;
        arcToBezier(path, n24 * cos - n25 * sin, n24 * sin + n25 * cos, n5, n6, n, n2, radians, atan2, n23);
    }
    
    public static void nodesToPath(final PathDataNode[] array, final Path path) {
        final float[] array2 = new float[6];
        char type = 'm';
        for (int i = 0; i < array.length; ++i) {
            addCommand(path, array2, type, array[i].type, array[i].params);
            type = array[i].type;
        }
    }
    
    public void interpolatePathDataNode(final PathDataNode pathDataNode, final PathDataNode pathDataNode2, final float n) {
        for (int i = 0; i < pathDataNode.params.length; ++i) {
            this.params[i] = pathDataNode.params[i] * (1.0f - n) + n * pathDataNode2.params[i];
        }
    }
}
