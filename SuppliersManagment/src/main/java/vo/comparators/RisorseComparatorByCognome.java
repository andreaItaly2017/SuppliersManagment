
package vo.comparators;

import java.util.Comparator;

import managedBean.FornitoriRisorse;

public class RisorseComparatorByCognome implements Comparator<FornitoriRisorse> {

	@Override
	public int compare(FornitoriRisorse o1, FornitoriRisorse o2) {
		return o1.getCognomeRisorsa().compareTo(o2.getCognomeRisorsa());
	}
}