package JavaRecommendation.model;

public class Rating implements Comparable<Rating>{
    private Integer item;
    private float value;

    public Rating(Integer newItem, float newValue){
        item = newItem;
        value = newValue;
    }

    /*
	 * Retorna o item que foi avaliado.
	 */
	public Integer getItem() {
		return item;
	}
	/*
	 * Retorna o valor da classificação 
	 * (pode ser usado em cálculos)
	 */
	public float getValue() {
		return value;
	}

    @Override
    public String toString() {
        String result = "(" + getItem() + ", " + getValue() + ")";
	    return result;
    }
    
    @Override
    public int compareTo(Rating other) {
        if (value < other.value) {
			return -1;
		}
		if (value > other.value) {
			return 1;
		}
		return 0;
    }
}
