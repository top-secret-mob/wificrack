package org.tudelft.aircrack.frame.management;

import org.codehaus.preon.annotation.Bound;
import org.tudelft.aircrack.frame.SubType;
import org.tudelft.aircrack.frame.management.field.ElementId;
import org.tudelft.aircrack.frame.management.field.InformationElement;
import org.tudelft.aircrack.frame.management.field.InformationElements;
import org.tudelft.aircrack.frame.visitor.FrameVisitor;

public class ProbeRequest extends ManagementFrame
{
	
	@Bound
	public InformationElements elements;
	
	public ProbeRequest()
	{
		frameControl.setSubType(SubType.ProbeRequest);
		elements = new InformationElements();
	}

	public InformationElements getElements()
	{
		return elements;
	}
	
	public void setElements(InformationElements elements)
	{
		this.elements = elements;
	}

	public String getSsid()
	{
		final InformationElement element = elements.getElement(ElementId.SSID);
		if (element != null)
			return element.getInformationAsString();
		return null;
	}
	
	public void setSsid(String ssid)
	{
		elements.replaceElement(new InformationElement(ElementId.SSID, ssid));
	}

	@Override
	public String toString()
	{
		String ret = super.toString() + " SSID: [" + getSsid() + "]\n";
		
		for (InformationElement element : elements.getElements())
			ret += "\t" + element.toString() + "\n";
		
		return ret;
	}
	
	@Override
	public void accept(FrameVisitor visitor)
	{
		visitor.visit(this);
	}
	
}
