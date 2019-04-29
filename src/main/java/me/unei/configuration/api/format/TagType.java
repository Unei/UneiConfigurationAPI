package me.unei.configuration.api.format;

import java.util.HashMap;
import java.util.Map;

public enum TagType
{
	TAG_End(0, "TAG_End"),
	
	TAG_Byte(1, true, "TAG_Byte"),
	TAG_Short(2, true, "TAG_Short"),
	TAG_Int(3, true, "TAG_Int"),
	TAG_Long(4, true, "TAG_Long"),
	TAG_Float(5, true, "TAG_Float"),
	TAG_Double(6, true, "TAG_Double"),
	
	TAG_Byte_Array(7, "TAG_Byte_Array"),
	TAG_String(8, "TAG_String"),
	TAG_List(9, "TAG_List"),
	TAG_Compound(10, "TAG_Compound"),
	TAG_Int_Array(11, "TAG_Int_Array"),
	TAG_Long_Array(12, "TAG_Long_Array"),
	
	Number_TAG(99, true, "Any Numeric Tag");
	
	private static final Map<Byte, TagType> BY_ID = new HashMap<>();
	
	static
	{
		for (TagType type : TagType.values())
		{
			BY_ID.put(Byte.valueOf(type.getId()), type);
		}
	}

	private final byte tagId;
	private final boolean isNumber;
	private final String tagName;

	private TagType(int id, String name)
	{
		this(id, false, name);
	}

	private TagType(int id, boolean isNumber, String name)
	{
		this.tagId = (byte) id;
		this.isNumber = isNumber;
		this.tagName = name;
	}

	public byte getId()
	{
		return this.tagId;
	}
	
	public boolean isNumberTag()
	{
		return this.isNumber;
	}
	
	public String getTagName()
	{
		return this.tagName;
	}
	
	public static TagType getByTypeId(byte type)
	{
		return BY_ID.get(Byte.valueOf(type));
	}
}