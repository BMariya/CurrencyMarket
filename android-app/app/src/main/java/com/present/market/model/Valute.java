package com.present.market.model;

import com.present.market.core.base.AppType;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Valute")
public final class Valute {
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
        return new AppType.AppAmount(this.mValue);
    }

    public AppType.AppAmount getRefValue(Valute refValute, AppType.AppAmount refAmount) {
        AppType.AppAmount result = new AppType.AppAmount(
                refAmount.value * (refValute.getValue().value / refValute.getNominal().value)
                / (this.getValue().value / this.getNominal().value));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Valute valute = (Valute) o;

        if (mNominal != valute.mNominal) return false;
        if (!mCharCode.equals(valute.mCharCode)) return false;
        if (!mName.equals(valute.mName)) return false;
        return mValue.equals(valute.mValue);
    }

    @Override
    public int hashCode() {
        int result = mCharCode.hashCode();
        result = 31 * result + mName.hashCode();
        result = 31 * result + mNominal;
        result = 31 * result + mValue.hashCode();
        return result;
    }
}
