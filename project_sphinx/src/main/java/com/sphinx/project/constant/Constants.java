package com.sphinx.project.constant;

public interface Constants {

    public interface STATUS {
        Integer ACTIVE = 1;
        Integer INACTIVE = 0;
        Integer THANH_CONG = 1;
        Integer THAT_BAI = 2;
    }
    public interface TYPE_FILE{

    }
    public interface MESSAGE {
        String SUCCESS = "Thành công";
        String ERROR = "Thất bại";
    }

    public interface CODE {
        Integer SUCCESS = 200;
        Integer ERROR = 500;
    }

    public interface FILE {
        String PRODUCES_EXCEL = "application/vnd.ms-excel";
        String PRODUCES_DOC = "application/msword";
        String DOT_LINE = "........................................................................................................."
                + "...............................................................................................";
    }

    public interface TYPE {
        Integer TEXT = 1;
        Integer NUMBER = 2;
        Integer TABLE = 3;
        Integer COMMENT = 4;
    }

    public interface SYMBOL_KEY{
        String START_KEY = "<<";
        String END_KEY = ">>";
    }


}
