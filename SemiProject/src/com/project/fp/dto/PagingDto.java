package com.project.fp.dto;

public class PagingDto {

	// 한 페이지에 출력할 게시글 수
	public static final int DISPLAY_BOARD = 10;
	// 한 블록에 출력할 페이지 수
	public static final int DISPLAY_PAGE = 5;

	private int nowPage; // 현재 페이지
	private int prevPage; // 이전 페이지
	private int nextPage; // 다음 페이지
	private int totalPage; // 전체 페이지 갯수

	private int totalBlock; // 전체 블록 갯수
	private int nowBlock; // 현제 페이지 블록
	private int prevBlock; // 이전 페이지 블록
	private int nextBlock; // 다음 페이지 블록

	private int pageBegin; // 해당 페이지의 첫번째 게시글 no
	private int pageEnd; // 해당 페이지의 마지막 게시글 no

	private int blockBegin; // 현재 블록의 시작 페이지 번호
	private int blockEnd; // 현재 블록의 마지막 페이지 번호
	private String s_c;
	private String s_t;
	private String category;
	// 파라미터 없는 생성자
	public PagingDto() {
	}

	// 생성자
	public PagingDto(int count, int nowPage) {
		nowBlock = 1; // 현재 페이지 블록
		this.nowPage = nowPage; // 현재 페이지
		setTotalPage(count); // 전체 페이지 수 구하기
		setPageRange(); // 페이지 번호 계산
		setTotalBlock(); // 전체 블록 수 계산
		setBlockRange(); // 페이지 블록 계산
	}
	public PagingDto(int count, int nowPage , String s_c, String s_t, String category) {
		nowBlock = 1; // 현재 페이지 블록
		this.nowPage = nowPage; // 현재 페이지
		setTotalPage(count); // 전체 페이지 수 구하기
		setPageRange(); // 페이지 번호 계산
		setTotalBlock(); // 전체 블록 수 계산
		setBlockRange(); // 페이지 블록 계산
		this.s_c = s_c;
		this.s_t = s_t;
		this.category = category;
	}
	

	public PagingDto(int count, int nowPage, String s_t) {
		nowBlock = 1; // 현재 페이지 블록
		this.nowPage = nowPage; // 현재 페이지
		setTotalPage(count); // 전체 페이지 수 구하기
		setPageRange(); // 페이지 번호 계산
		setTotalBlock(); // 전체 블록 수 계산
		setBlockRange(); // 페이지 블록 계산
		this.s_t = s_t;
	}

	// 페이지 블록 계산
	public void setBlockRange() {
		// 1. 현재 페이지가 몇 번째 페이지 블록에 속하는지 계산
		nowBlock = (int) Math.ceil((nowPage - 1) / DISPLAY_PAGE) + 1;
		// 2. 현재 페이지 블록의 시작 페이지, 마지막 페이지 계산
		blockBegin = (nowBlock - 1) * DISPLAY_PAGE + 1;
		blockEnd = blockBegin + DISPLAY_PAGE - 1;
		// 3. 마지막 블록이 범위를 초과하지 않도록 계산
		if (blockEnd > totalPage) {
			blockEnd = totalPage;
		} 
		// 4. 이전을 눌렀을 때 이동할 페이지 번호
		prevPage = (nowPage == 1) ? 1 : (nowBlock - 1) * DISPLAY_PAGE;
		// 5. 다음을 눌렀을 때 이동할 페이지 번호
		nextPage = (nowBlock > totalBlock) ? (nowBlock * DISPLAY_PAGE) : (nowBlock * DISPLAY_PAGE) + 1;
		if (nextPage >= totalPage) {
			nextPage = totalPage;
		}
	}

	public String getS_c() {
		return s_c;
	}

	public void setS_c(String s_c) {
		this.s_c = s_c;
	}

	public String getS_t() {
		return s_t;
	}

	public void setS_t(String s_t) {
		this.s_t = s_t;
	}

	// 페이지 범위 계산
	public void setPageRange() {
		pageBegin = (nowPage - 1) * DISPLAY_BOARD + 1;
		pageEnd = pageBegin + DISPLAY_BOARD - 1;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int count) {
		this.totalPage = (int) Math.ceil(count / (double)DISPLAY_BOARD);
	}

	public int getTotalBlock() {
		return totalBlock;
	}

	public void setTotalBlock() {
		this.totalBlock = (int) Math.ceil(totalPage / (double) DISPLAY_PAGE);
	}

	public int getNowBlock() {
		return nowBlock;
	}

	public void setNowBlock(int nowBlock) {
		this.nowBlock = nowBlock;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}

	public int getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}

	public int getPageBegin() {
		return pageBegin;
	}

	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public int getBlockBegin() {
		return blockBegin;
	}

	public void setBlockBegin(int blockBegin) {
		this.blockBegin = blockBegin;
	}

	public int getBlockEnd() {
		return blockEnd;
	}

	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}

	public static int getDisplayBoard() {
		return DISPLAY_BOARD;
	}

	public static int getDisplayPage() {
		return DISPLAY_PAGE;
	}

}

