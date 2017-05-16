
package vo.comparators;

import java.util.Comparator;

import managedBean.FornitoriRisorse;

public class RisorseComparatorById implements Comparator<FornitoriRisorse>{

	@Override
	public int compare(FornitoriRisorse o1, FornitoriRisorse o2) {
		return  new Long(o1.getIdRisorsa()).compareTo(new Long(o2.getIdRisorsa()));
	}
}