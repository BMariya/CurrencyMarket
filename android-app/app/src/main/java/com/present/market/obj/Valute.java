package com.present.market.obj;

import com.present.market.core.base.AbsObj;
import com.present.market.core.base.AppType;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Valute")
public final class Valute extends AbsObj {
    @Attribute(name = "ID")
    private String mId;
    @Element(name = "NumCode")
    private String mNumCode;
    @Element(name = "CharCode")
    private String mCharCode;
    @Element(name = "Name")
    private String mName;
    @Element(name = "Nominal")
    private int mNominal;
    @Element(name = "Value")
    private String mValue;

    private Valute() {
        super();
    }
    public Valute(String charCode, String name, int nominal, String value) {
        this();
        log().debug("charCode=%s,name=%s,nominal=%s,value=%s", charCode, name, nominal, value);
        this.mCharCode = charCode;
        this.mName = name;
        this.mNominal = nominal;
        this.mValue = value;
        this.mId = "UNDEFINED";
        this.mNumCode = "UNDEFINED";
    }

    public String getCharCode() {
        return this.mCharCode;
    }

    public String getName() {
        return this.mName;
    }

    public AppType.AppAmount getNominal() {
        return new AppType.AppAmount(this.mNominal);
    }

    public AppType.AppAmount getValue() {
        log().todo("getValue.use XML Converter, or Transformer,...");
        return new AppType.AppAmount(this.mValue);
    }

    public AppType.AppAmount getRefValue(Valute refValute, AppType.AppAmount refAmount) {
        log().debug("getRefValue.refValute=%s,refAmount=%s", refValute, refAmount);
        AppType.AppAmount result = new AppType.AppAmount(
                refAmount.value * (refValute.getValue().value / refValute.getNominal().value)
                / (this.getValue().value / this.getNominal().value));
        log().debug("getRefValue.result=%s", result);
        return result;
    }

    public final boolean equals(Valute value) {
        return this.getCharCode().equals(value.getCharCode())
                && this.getName().equals(value.getName())
                && this.getNominal() == value.getNominal()
                && this.getValue().equals(value.getValue());
    }
}
