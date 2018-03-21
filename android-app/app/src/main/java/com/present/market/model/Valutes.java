package com.present.market.model;

import com.present.market.core.base.AppType;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "ValCurs")
public final class Valutes {
    @Attribute(name = "name")
    private String mName;
    @Attribute(name = "Date")
    private String mDate;
    @ElementList(inline = true)
    private List<Valute> mValuteList;

    public String getName() {
        return this.mName;
    }

    public AppType.AppDate getDate() {
        return new AppType.AppDate(this.mDate);
    }

    public List<Valute> getValuteList() {
        return this.mValuteList;
    }
}
