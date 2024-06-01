class ComplexNumber {

    private final double re;
    private final double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexNumber)) return false;

        ComplexNumber that = (ComplexNumber) o;
        return Double.compare(getRe(), that.getRe()) == 0 && Double.compare(getIm(), that.getIm()) == 0;
    }

    @Override
    public int hashCode() {
        int result = Double.hashCode(getRe());
        result = 31 * result + Double.hashCode(getIm());
        return result;
    }
}