package evtconvertor.basis;

import org.eventb.core.ICommentedElement;
import org.eventb.core.IExpressionElement;
import org.rodinp.core.IInternalElementType;
import org.rodinp.core.RodinCore;

import evtconvertor.Activator;

public interface IBound extends ICommentedElement, IExpressionElement {
	IInternalElementType<IBound> ELEMENT_TYPE = RodinCore
			.getInternalElementType(Activator.PLUGIN_ID + ".bound");
}
