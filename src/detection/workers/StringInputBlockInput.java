package detection.workers;

import input.block.InputBlock;
import util.blockingqueue.IReceiver;

public class StringInputBlockInput implements InputBlockInput {

	private IReceiver<String> input;
	
	public StringInputBlockInput(IReceiver<String> input) {
		this.input = input;
	}
	
	@Override
	public InputBlock take() {
		while(true) {
			try {
				String sblock = input.take(); 
				return InputBlock.readDetectionBlock(sblock);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean isPoisoned() {
		return input.isPoisoned();
	}

}
