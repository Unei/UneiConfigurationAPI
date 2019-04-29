package me.unei.configuration.api.format;

import java.util.HashMap;
import java.util.Map;

import me.unei.configuration.StaticInstance;
import me.unei.configuration.StaticInstance.StaticInstanceExposer;

/**
 * Used to determine a type of a NBT tag. 
 */
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

	/**
	 * Gets the ID of this type.
	 * <p>
	 * Prefer not using it if there is another possibility.
	 * 
	 * @return the ID of the NBT tag.
	 */
	public byte getId()
	{
		return this.tagId;
	}
	
	/**
	 * Returns <tt>true</tt> whenever tag of this types are containing numbers.
	 * 
	 * @return <tt>true</tt> whenever tag of this types are containing numbers
	 */
	public boolean isNumberTag()
	{
		return this.isNumber;
	}
	
	/**
	 * Gets the name of the tag.
	 * 
	 * @return the name of the tag
	 */
	public String getTagName()
	{
		return this.tagName;
	}
	
	/**
	 * Create a new instance of this tag if it can be externally created.
	 * <p>
	 * For instance: a string or an integer tag cannot be created.<br>
	 * On the contrary, a Compound or a List tag can be created.
	 * 
	 * @return The newly created tag, or <tt>null</tt> if it cannot be build.
	 * 
	 * @throws IllegalStateException if the plugin is not fully loaded and the
	 * implementation of this method is not (yet ?) available.
	 */
	public INBTTag newInstance()
	{
		return instance().internal_createTag(this);
	}
	
	/**
	 * Get a tag by it's ID.
	 * 
	 * @see #getId()
	 * 
	 * @param type the ID of the type.
	 * @return the type if found, <tt>null</tt> otherwise.
	 */
	public static TagType getByTypeId(byte type)
	{
		return BY_ID.get(Byte.valueOf(type));
	}
	
	private static final StaticInstance<ATagCreator> Instance = new StaticInstance<>();
	public static final StaticInstanceExposer<ATagCreator> INSTANCE = new StaticInstanceExposer<>(Instance, false);
	
	static
	{
		try {
			Instance.setConstructor(Class.forName("me.unei.configuration.formats.nbtlib.TagCreatorImpl"), "init");
		} catch (ClassNotFoundException e) {
			;
		}
	}
	
	private static ATagCreator instance() {
		if (!Instance.isEmpty()) {
			return Instance.get();
		}
		Instance.callBuilder();
		if (!Instance.isEmpty()) {
			return Instance.get();
		}
		throw new IllegalStateException("TagCreator has yet to be enabled");
	}
	
	public static abstract class ATagCreator
	{
		protected final void setInstance() {
			if (Instance.isEmpty()) {
				Instance.set(this);
			}
		}
		
		protected abstract INBTTag internal_createTag(TagType type);
		protected INBTCompound internal_createCompound() { return (INBTCompound) internal_createTag(TAG_Compound); }
		protected INBTList internal_createList() { return (INBTList) internal_createTag(TAG_List); }
	}
}