package com.demo.touchstone.grammarapicheck;

import java.util.List;

/**
 * Created by touchstone on 4/3/2017.
 */

public class GrammarResponse {


    /**
     * result : true
     * errors : [{"id":"edf0eb71","offset":0,"length":4,"bad":"your","better":["Your"]},{"id":"edf0eb72","offset":12,"length":7,"bad":"goodest","better":["best","guides","gooiest","good est"]}]
     * score : 25
     */

    private boolean result;
    private int score;
    private List<ErrorsBean> errors;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<ErrorsBean> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorsBean> errors) {
        this.errors = errors;
    }

    public static class ErrorsBean {
        /**
         * id : edf0eb71
         * offset : 0
         * length : 4
         * bad : your
         * better : ["Your"]
         */

        private String id;
        private int offset;
        private int length;
        private String bad;
        private List<String> better;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public String getBad() {
            return bad;
        }

        public void setBad(String bad) {
            this.bad = bad;
        }

        public List<String> getBetter() {
            return better;
        }

        public void setBetter(List<String> better) {
            this.better = better;
        }
    }
}
