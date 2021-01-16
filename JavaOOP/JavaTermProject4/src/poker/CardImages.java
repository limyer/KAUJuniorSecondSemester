package poker;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;


public final class CardImages 
{
	private static final String IMAGE_LOCATION = Paths.get("").toAbsolutePath().toString() + "\\src\\images\\";
	private static final String IMAGE_SUFFIX = ".gif";	
	
	private static Map<String, ImageIcon> aCards = new HashMap<String, ImageIcon>();
	
	private CardImages()
	{}
	
	
	public static ImageIcon getCard( Card pCard )
	{
		return getCard( pCard.toString() );
	}
	

	public static ImageIcon getBack()
	{
		return getCard( "b" );
	}
	

	
	public static ImageIcon getCard( String pCode )
	{
		ImageIcon lIcon = (ImageIcon) aCards.get( pCode );
		if( lIcon == null )
		{
			lIcon = new ImageIcon( IMAGE_LOCATION + pCode + IMAGE_SUFFIX );
			aCards.put( pCode, lIcon );
		}
		return lIcon;
	}
}
