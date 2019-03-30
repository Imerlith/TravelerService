package zad1;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@ElementList
public class tabela_kursow {
    @Attribute
    public String uid;

    @Attribute
    public String typ;

    @Element
    public String numer_tabeli;

    @Element
    public String data_publikacji;

    @ElementList(inline = true)
    public List<pozycja> list;


}
@Root(strict = false)
class pozycja {
    @Element
    public String kod_waluty;

    @Element
    public String kurs_sredni;

    @Override
    public String toString() {
        return kod_waluty+" : "+kurs_sredni;
    }
}
