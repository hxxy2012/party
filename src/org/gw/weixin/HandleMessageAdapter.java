package org.gw.weixin;

import org.gw.weixin.msg.Msg4Event;
import org.gw.weixin.msg.Msg4Image;
import org.gw.weixin.msg.Msg4Link;
import org.gw.weixin.msg.Msg4Location;
import org.gw.weixin.msg.Msg4Text;
import org.gw.weixin.msg.Msg4Video;
import org.gw.weixin.msg.Msg4Voice;



/**
 * 处理消息适配器(适配器模式)
 * @author fuyun
 * */
public class HandleMessageAdapter implements HandleMessageListener {

	public void onTextMsg(Msg4Text msg) {
		
	}

	public void onImageMsg(Msg4Image msg) {
		
	}

	public void onEventMsg(Msg4Event msg) {
		
	}

	public void onLinkMsg(Msg4Link msg) {
		
	}

	public void onLocationMsg(Msg4Location msg) {
		
	}

	public void onErrorMsg(int errorCode) {
		
	}

	/* (non-Javadoc)
	 * @see org.marker.weixin.HandleMessageListener#onVoiceMsg(org.marker.weixin.msg.Msg4Voice)
	 */
	public void onVoiceMsg(Msg4Voice msg) {
		
	}

	/* (non-Javadoc)
	 * @see org.marker.weixin.HandleMessageListener#onVideoMsg(org.marker.weixin.msg.Msg4Video)
	 */
	public void onVideoMsg(Msg4Video msg) {
		
	}

}
