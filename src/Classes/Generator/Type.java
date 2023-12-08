package Classes.Generator;
public enum Type {
    ASIGN        ("Asign"       ) ,
    CALLFUNCTION ("CallFunction") ,
    END          ("End"         ) ,
    EXPRESSION   ("Expression"  ) ,
    FUNCTION     ("Function"    ) ,
    GENERIC      ("Generic"     ) ,
    GETHEAP      ("GetHeap"     ) ,
    GETSTACK     ("GetStack"    ) ,
    GOTO         ("Goto"        ) ,
    IF           ("If"          ) ,
    LABEL        ("Label"       ) ,
    PRINTF       ("Printf"      ) ,
    RETURN       ("Return"      ) ,
    SETHEAP      ("SetHeap"     ) ,
    SETSTACK     ("SetStack"    ) ;
    private String value;
    private Type(String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
}