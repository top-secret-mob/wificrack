package org.tudelft.aircrack.frame.data;

import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.If;
import org.codehaus.preon.annotation.Order;
import org.codehaus.preon.el.ImportStatic;
import org.tudelft.aircrack.frame.Address;
import org.tudelft.aircrack.frame.Frame;
import org.tudelft.aircrack.frame.SequenceControl;
import org.tudelft.aircrack.frame.SubType;
import org.tudelft.aircrack.frame.data.field.FrameBody;
import org.tudelft.aircrack.frame.data.field.QualityOfServiceControl;
import org.tudelft.aircrack.frame.visitor.FrameVisitor;

@ImportStatic(SubType.class)
public class DataFrame extends Frame
{
	
	@BoundNumber(size="16")
	@Order(1) private int duration;
	
	@Bound
	@Order(2) private Address address1;

	@Bound
	@Order(3) private Address address2;
	
	@Bound
	@Order(4) private Address address3;
	
	@Bound
	@Order(5) private SequenceControl sequenceControl;

	@If("frameControl.subType < 4 || (frameControl.subType >= 8 && frameControl.subType < 12)")
	@Bound
	@Order(6) private Address address4;
	
	@If("frameControl.subType >= 8")
	@Bound
	@Order(7) private QualityOfServiceControl qualityOfServiceControl;
	
	@FrameBody
	@Order(8) private byte[] body;

	public int getDuration()
	{
		return duration;
	}
	
	public SequenceControl getSequenceControl()
	{
		return sequenceControl;
	}
	
	public Address getAddress1()
	{
		return address1;
	}
	
	public Address getAddress2()
	{
		return address2;
	}
	
	public Address getAddress3()
	{
		return address3;
	}
	
	public Address getAddress4()
	{
		return address4;
	}

	public void setDuration(int duration)
	{
		this.duration = duration;
	}
	
	public void setSequenceControl(SequenceControl sequenceControl)
	{
		this.sequenceControl = sequenceControl;
	}
	
	public void setAddress1(Address address1)
	{
		this.address1 = address1;
	}
	
	public void setAddress2(Address address2)
	{
		this.address2 = address2;
	}
	
	public void setAddress3(Address address3)
	{
		this.address3 = address3;
	}
	
	public void setAddress4(Address address4)
	{
		this.address4 = address4;
	}
	
	public QualityOfServiceControl getQualityOfServiceControl()
	{
		return qualityOfServiceControl;
	}
	
	public void setQualityOfServiceControl(QualityOfServiceControl qualityOfServiceControl)
	{
		this.qualityOfServiceControl = qualityOfServiceControl;
	}
	
	public byte[] getBody()
	{
		return body;
	}
	
	public void setBody(byte[] body)
	{
		this.body = body;
	}
	
	public Address getRA()
	{
		return address1;		
	}
	
	public Address getTA()
	{
		return address2;
	}
	
	public Address getBssId()
	{
		if (frameControl.getToDs())
		{
			if (frameControl.getFromDs())
				return null;
			else
				return address1;
		} else
		{
			if (frameControl.getFromDs())
				return address2;
			else
				return address3;
		}
	}
	
	public Address getDA()
	{
		if (frameControl.getToDs())
			return address3;
		else
			return address1;
	}
	
	public Address getSA()
	{
		if (frameControl.getToDs())
		{
			if (frameControl.getFromDs())
				return address4;
			else
				return address2;
		} else
		{
			if (frameControl.getFromDs())
				return address3;
			else
				return address2;
		}
	}

	@Override
	public String toString() {
		return "DataFrame{" +
				"address1=" + address1 +
				", address2=" + address2 +
				", address3=" + address3 +
				", address4=" + address4 +
				'}';
	}

	@Override
	public void accept(FrameVisitor visitor)
	{
		visitor.visit(this);
	}

}
