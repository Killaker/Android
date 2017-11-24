package com.facebook.share.internal;

import com.facebook.share.model.*;
import com.facebook.internal.*;
import java.util.*;

public class GameRequestValidation
{
    public static void validate(final GameRequestContent gameRequestContent) {
        Validate.notNull(gameRequestContent.getMessage(), "message");
        final boolean b = gameRequestContent.getObjectId() != null;
        boolean b2 = false;
        Label_0050: {
            if (gameRequestContent.getActionType() != GameRequestContent.ActionType.ASKFOR) {
                final GameRequestContent.ActionType actionType = gameRequestContent.getActionType();
                final GameRequestContent.ActionType send = GameRequestContent.ActionType.SEND;
                b2 = false;
                if (actionType != send) {
                    break Label_0050;
                }
            }
            b2 = true;
        }
        if (b ^ b2) {
            throw new IllegalArgumentException("Object id should be provided if and only if action type is send or askfor");
        }
        final List<String> recipients = gameRequestContent.getRecipients();
        int n = 0;
        if (recipients != null) {
            n = 0 + 1;
        }
        if (gameRequestContent.getSuggestions() != null) {
            ++n;
        }
        if (gameRequestContent.getFilters() != null) {
            ++n;
        }
        if (n > 1) {
            throw new IllegalArgumentException("Parameters to, filters and suggestions are mutually exclusive");
        }
    }
}
