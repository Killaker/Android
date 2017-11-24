package bf.io.openshop.entities.filtr;

import java.util.*;
import java.lang.reflect.*;
import com.google.gson.*;

public class DeserializerFilters implements JsonDeserializer<Filters>
{
    public static final String FILTER_TYPE_COLOR = "color";
    public static final String FILTER_TYPE_RANGE = "range";
    public static final String FILTER_TYPE_SELECT = "select";
    public static final String TAG_ID = "id";
    public static final String TAG_LABEL = "label";
    public static final String TAG_NAME = "name";
    public static final String TAG_TYPE = "type";
    public static final String TAG_VALUES = "values";
    
    private void parseGeneralFields(final JsonObject jsonObject, final FilterType filterType) {
        if (jsonObject.has("id")) {
            filterType.setId(jsonObject.get("id").getAsLong());
        }
        if (jsonObject.has("name")) {
            filterType.setName(jsonObject.get("name").getAsString());
        }
        if (jsonObject.has("label")) {
            filterType.setLabel(jsonObject.get("label").getAsString());
        }
    }
    
    private <T> List<T> parseTypeValues(final Class<T> clazz, final JsonObject jsonObject, final JsonDeserializationContext jsonDeserializationContext) {
        final boolean has = jsonObject.has("values");
        List<T> list = null;
        if (has) {
            final JsonArray asJsonArray = jsonObject.get("values").getAsJsonArray();
            list = new ArrayList<T>();
            for (int i = 0; i < asJsonArray.size(); ++i) {
                list.add(clazz.cast(jsonDeserializationContext.deserialize(asJsonArray.get(i), clazz)));
            }
        }
        if (list == null || list.size() == 0) {
            list = null;
        }
        return list;
    }
    
    @Override
    public Filters deserialize(final JsonElement jsonElement, final Type type, final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (jsonElement.isJsonArray()) {
            final Filters filters = new Filters();
            final ArrayList filters2 = new ArrayList<FilterType>();
            final JsonArray jsonArray = (JsonArray)jsonElement;
            for (int i = 0; i < jsonArray.size(); ++i) {
                final JsonElement value = jsonArray.get(i);
                if (value.isJsonObject()) {
                    final JsonObject jsonObject = (JsonObject)value;
                    if (jsonObject.has("type")) {
                        final String asString = jsonObject.get("type").getAsString();
                        if ("color".equals(asString)) {
                            final FilterTypeColor filterTypeColor = new FilterTypeColor();
                            filterTypeColor.setType("color");
                            this.parseGeneralFields(jsonObject, filterTypeColor);
                            filterTypeColor.setValues(this.parseTypeValues(FilterValueColor.class, jsonObject, jsonDeserializationContext));
                            if (filterTypeColor.getValues() != null) {
                                filters2.add(filterTypeColor);
                            }
                        }
                        else if ("select".equals(asString)) {
                            final FilterTypeSelect filterTypeSelect = new FilterTypeSelect();
                            filterTypeSelect.setType("select");
                            this.parseGeneralFields(jsonObject, filterTypeSelect);
                            filterTypeSelect.setValues(this.parseTypeValues(FilterValueSelect.class, jsonObject, jsonDeserializationContext));
                            if (filterTypeSelect.getValues() != null) {
                                filters2.add((FilterTypeColor)filterTypeSelect);
                            }
                        }
                        else if ("range".equals(asString)) {
                            final FilterTypeRange filterTypeRange = new FilterTypeRange();
                            filterTypeRange.setType("range");
                            this.parseGeneralFields(jsonObject, filterTypeRange);
                            if (jsonObject.has("values")) {
                                final JsonArray asJsonArray = jsonObject.get("values").getAsJsonArray();
                                if (asJsonArray != null && asJsonArray.size() == 3) {
                                    filterTypeRange.setMin(asJsonArray.get(0).getAsInt());
                                    filterTypeRange.setMax(asJsonArray.get(1).getAsInt());
                                    filterTypeRange.setRangeTitle(asJsonArray.get(2).getAsString());
                                }
                            }
                            filters2.add((FilterTypeColor)filterTypeRange);
                        }
                    }
                }
            }
            if (filters2.size() > 0) {
                filters.setFilters(filters2);
            }
            return filters;
        }
        throw new JsonParseException("Unexpected JSON type: " + jsonElement.getClass().getSimpleName());
    }
}
