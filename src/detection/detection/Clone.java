package detection.detection;

public class Clone {
	
	private long fileid1;
	private int startline1;
	private int endline1;
	
	private long fileid2;
	private int startline2;
	private int endline2;
	
	public Clone(long fileid1, int startline1, int endline1, long fileid2, int startline2, int endline2) {
		super();
		
		boolean inOrder;
		if(fileid1 < fileid2) {
			inOrder = true;
		} else if (fileid1 > fileid2) {
			inOrder = false;
		} else {
			if(startline1 < startline2) {
				inOrder = true;
			} else if (startline1 > startline2) {
				inOrder = false;
			} else {
				if(endline1 < endline2) {
					inOrder = true;
				} else if (endline1 > endline2) {
					inOrder = false;
				} else {
					inOrder = true;
				}
			}
		}
		
		if(inOrder) {
			this.fileid1 = fileid1;
			this.startline1 = startline1;
			this.endline1 = endline1;
			this.fileid2 = fileid2;
			this.startline2 = startline2;
			this.endline2 = endline2;
		} else {
			this.fileid2 = fileid1;
			this.startline2 = startline1;
			this.endline2 = endline1;
			this.fileid1 = fileid2;
			this.startline1 = startline2;
			this.endline1 = endline2;
		}
	}

	public long getFileid1() {
		return fileid1;
	}
	
	public int getStartline1() {
		return startline1;
	}
	
	public int getEndline1() {
		return endline1;
	}
	
	public long getFileid2() {
		return fileid2;
	}
	
	public int getStartline2() {
		return startline2;
	}
	
	public int getEndline2() {
		return endline2;
	}
	
}
