import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;

public class LegoStore {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

///////////////////////////////////////////////////////////////////////////
		
		// 각 변수의 정보를 담은 리스트
		// {{변수 번호}, {변수 가중치}, {변수값}}
		// 변수 가중치의 초기값은 0, 변수값의 초기값은 -1이다.
		int[][] Vlist = { { 1, 2, 3, 4, 5, 6 }, { 70, 50, 40, 0, 0, 0 }, { 0, 0, 0, -1, -1, -1 } };

		// 각 변수의 변수명 리스트, (현재 4,5,6번은 비어있는 상태이다.)
		String[] VlistName = { "점심 메뉴의 맛있는 정도", "요일 스트레스", "시험까지 남은 날짜", "", "", "" };

		// (독립변수인)시간 대와 각 변수의 연관성 수치(1~10)
		// {12교시,34교시,567교시}가 각 항목마다 있음 (따라서 {{1번 변수와 시간 대의 연관성}, {2번 변수와...},...}
		int[][] Classcnt = { { 5, 10, 10 }, { 7, 5, 8 }, { 5, 5, 5 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };

		// 빈복문 실행을 위한 변수
		int finish = 0;
		
///////////////////////////////////////////////////////////////////////////
		
		// 1차 실행 화면_menu1
		System.out.println("=========가장 효율적인 매점 방문 시간 대를 분석합니다=========");
		System.out.println("*현재 변수 우선 순위: 0. 시간 대\n1. 점심 메뉴의 맛있는 정도\t2. 요일 스트레스\t3. 시험까지 남은 날짜"); // 초기 실행 시, 항상 일정
		System.out.println("=================================================");

///////////////////////////////////////////////////////////////////////////
		
		// 반복문 시작
		while (finish == 0) {
			System.out.println("실행 메뉴 선택: 0. 변수 설정 조정\t1.바로 분석"); // menu1

///////////////////////////////////////////////////////////////////////////
			
			// menu1 실행 변수
			int menu1 = sc.nextInt();

			// menu1 본 실행문
			// menu1_0.변수 설정 조정 메뉴 선택 시,
			if (menu1 == 0) {
				// 2-1차 실행 화면_menu2
				System.out.println("변수 설정을 조정합니다.");
				System.out.println("실행 메뉴 선택: 1. 변수 제거\t2. 변수 추가\t3. 변수 수정");

				// menu2_선택 메뉴
				int menu2 = sc.nextInt();

				// menu2 본 실행문
				switch (menu2) {
				// menu2_1. 변수 제거 메뉴 선택 시,
				case 1: {
					System.out.println("제거할 변수의 번호를 입력해주세요:");
					System.out.println("(단, 시간 대 변수는 제거할 수 없습니다.)"); // 시간 대는 0번 고정 변수

					// 변수의 축약 정보 제공
					PrintVLess(VlistName);
					System.out.println("\n");

					// 제거 변수 번호 선택
					int del = sc.nextInt();

					// 변수 제거 함수 실행
					DelV(del, Vlist, VlistName, Classcnt);

					System.out.println("\n변수 설정이 업데이트 되었습니다.");

					// 현재 변수 상태 자세히 출력
					PrintV(Vlist, VlistName, Classcnt);
					break;
				}

				// menu2_2.변수 추가 메뉴 선택 시,
				case 2: {
					System.out.println("추가할 변수 명을 입력하세요.(최대 6변수 가능, 띄어쓰기 불가능):");
					System.out.println("(새로운 변수는 1~100 사이의 정수로 수치화할 수 있어야 합니다.)");
					System.out.println("(변수값과 학생들의 매점방문률은 반비례해야 합니다.)");
					System.out.println("*6변수 이상 입력 시, 가장 가중치가 작은 변수가 제거됩니다.*");

					// 새로운 변수 명 설정_next를 사용하였기 때문에 띄어쓰기 불가
					String newVName = sc.next();

					// 가중치 설정
					System.out.println(newVName + "의 가중치를 입력하세요.(1~100 정수):");
					int newVWeight = sc.nextInt();

					// 변수 값 설정
					System.out.println("변수 값을 설정해주세요.(1~100 정수):");
					System.out.println("(입력받을 시 0으로 설정)");
					int Val = sc.nextInt();

					// 연관성 수치 설정
					System.out.println("고정 변수와의 연관성 수치1,2,3을 입력하세요:");
					int[] Cnt = new int[3];
					for (int i = 0; i <= 2; i++) {
						Cnt[i] = sc.nextInt();
					}

					// 모든 배열에 새로운 변수 정보 추가
					AddV(newVWeight, newVName, Val, Cnt, Vlist, VlistName, Classcnt);

					System.out.println("\n변수 설정이 업데이트 되었습니다.");

					// 현재 변수 상태 자세히 출력
					PrintV(Vlist, VlistName, Classcnt);

					break;
				}

				// menu2_3. 변수 수정 메뉴 선택 시,
				case 3: {
					System.out.println("수정할 변수 번호를 입력하세요:");

					// 모든 변수에 대한 축약 정보 제공
					PrintVLess(VlistName);
					System.out.println("\n");

					// 수정할 변수 번호 선택
					int modi = sc.nextInt();

					// 해당 변수만에 대한 자세한 정보 제공
					PrintVMore(modi, Vlist, VlistName, Classcnt);

					// 변수명 변경
					System.out.println("변수명 변경값을 입력하세요(띄어쓰기 불가능):");
					String modiName = sc.next();
					// 변수명 변경 함수
					Name(modi, modiName, VlistName);

					// 가중치 변경_불가 :: 제거->추가 방법으로 가능
					System.out.println("가중치는 변경할 수 없습니다. 제거 후 추가 요망");

					// 변수 값 변경
					System.out.println("변수값 변경값을 입력하세요.(1~100 정수):");
					System.out.println("(입력받을 시 0으로 설정)");
					int modiVal = sc.nextInt();
					// 변수값 변경 함수
					Value(modi, modiVal, Vlist);

					// 연관성 수치 변경
					System.out.println("연관성 수치 변경값1,2,3을 입력하세요.:");
					int[] Cnt2 = new int[3];
					for (int i = 0; i <= 2; i++) {
						Cnt2[i] = sc.nextInt();
					}
					// 연관성 수치 변경 함수
					Connect(modi, Cnt2, Classcnt);

					System.out.println("\n변수 설정이 업데이트 되었습니다.");

					// 현재 변수 상태 자세히 출력
					PrintV(Vlist, VlistName, Classcnt);
					break;
				}
				}// switch문 종료
			} // menu1=0 종료 (menu1=0에 경우에서는 항상 while 반복문 안에...==> 저장한 뒤, 처음부터 재실행)

///////////////////////////////////////////////////////////////////////////
			
			// menu1_1. 바로분석 메뉴 실행
			else if (menu1 == 1) {
				System.out.println("최적 시간 분석을 시작합니다.");
				// 1~7교시 쉬는 시간 중 불가능한 시간 입력 받음
				System.out.println("시간이 지났거나 방문이 불가능한 시간대의 수를 입력해주세요.:");
				int numtime = sc.nextInt(); // 불가능한 시간대의 개수

				System.out.println("시간이 지났거나 방문이 불가능한 시간대를 입력해주세요.:");
				System.out.println("(n 입력 시, n교시 쉬는 시간에 해당)");

				int[] timeList = { 1, 2, 3, 4, 5, 6, 7 }; // 모든 교시에 대한 배열
				for (int i = 0; i < numtime; i++) {
					// 불가능한 시간대를 1차적으로 담은 변수
					int noTime = sc.nextInt();

					// 해당 불가능한 시간 변수를 timeList 배열에서 제거(7교시가 입력되었으면 원소 7을 0으로 변환)
					timeList[noTime - 1] = 0;
				} // 불가능한 시간대 설정 끝

				// 변수에 관한 본 실행문
				// 예외 변수부터 다룬다.
				// 예외변수) 모든 변수는 1~100 사이의 정수로 변수값을 입력받을 수 있지만,
				// 요일 변수는 불가능하다. 따라서 따로 케이스를 분류해준다.

				// 요일이 사용자에 의해 제거되지 않았다면,
				if (Arrays.asList(Vlist).contains("요일")) {
					System.out.println("오늘의 요일을 입력하세요:");
					String Day = sc.nextLine();

					// 요일 변수의 번호(=인덱스+1)를 찾기위해(사용자에 의해 변경되었을 수도 있으니)
					// 해당 인덱스 값을 부여할 변수 daynum 설정
					int daynum = -1; // 초기값은 배열 순서에 영향받지 않기 위해 -1로 설정

					for (int i = 0; i <= 5; i++) {
						// 요일의 인덱스 찾기
						// "요일"이 VlistName의 변수명과 일치하면...
						if (VlistName[i].equals("요일")) {
							daynum = i;
							break;
						}

						// 인덱스값을 찾았으면, 현재의 요일에 따라 다른 변수값을 배열 Vlist[2][인덱스]에 심어준다.
						// 월, 화요일인 경우
						if (Day.equals("월요일") || Day.equals("화요일")) {

							Vlist[2][daynum] = 60;
						}

						// 수요일인 경우
						else if (Day.equals("수요일")) {
							Vlist[2][daynum] = 40;
						}

						// 목, 금요일인 경우
						else if (Day.equals("목요일") || Day.equals("금요일")) {
							Vlist[2][daynum] = 40;
						}
					}
				} // 예외 변수에 대한 처리 끝

				// 일반 변수에 관해...
				// 예외 변수가 아닌 일반 변수들 중, 변수값이 현재 0으로 설정되어
				// 사용자로부터 입력받아야하는 경우를 위한 자동 실행
				int getVal;
				for (int i=0; i<=5; i++) {
					if (Vlist[2][i] == 0) {//변수값이 0이어서 입력 받아야 하는 경우
						
						// 자동 수치 입력->저장 실행문 실행
						System.out.printf("%s의 수치를 1~100 사이의 정수로 입력하세요:", VlistName[i]);
						
						// 입력받은 수치(변수값)
						getVal = sc.nextInt();
						Vlist[2][i] = getVal; // 변수값을 Vlist의 변수값을 저장하는 배열에 저장
					}
				}
				// 모든 계산 가능한 정보가 배열에 입력되었으므로, 계산함수(Calcul) 실행
				int answer = Calcul(timeList, Vlist, Classcnt);// 리턴된 교시 수를 answer에 저장
				System.out.printf("\n가장 효율적인 매점 방문 시간은  %d교시 쉬는 시간입니다.\n시간에 맞춰 방문하세요!", answer);
				finish = -1; // 가장 큰 while 반복문 종료를 위한 변수값 변경
			} // else if 문 종료
			System.out.println("\n=================================================");
		} // while문 종료
		System.out.println("\n시스템을 종료합니다.");
		sc.close();
	}// main method end

///////////////////////////////////////////////////////////////////////////
	
	// method 모음

	// 변수를 제거하기 위한 method
	public static void DelV(int del, int[][] Vlist, String[] VlistName, int[][] Classcnt) {
		int num = del - 1; // 인덱스 = 변수 번호-1
		if (num != 5) {// 삭제하는 것이 5번 인덱스가 아닌 경우에,
			for (int i = 0; i <= 2; i++) {
				for (int j = num; j <= 4; j++) {
					Vlist[i][j] = Vlist[i][j + 1];
					// Vlist에서 제거할 변수의 인덱스보다
					// 더 큰 인덱스를 가진 변수들을 앞으로 한 칸씩 당긴다.
				}
			}
			// VlistName, Classcnt에 대해서도 동일하게 실행
			for (int i = num; i <= 4; i++) {
				VlistName[i] = VlistName[i + 1];
				Classcnt[i] = Classcnt[i + 1];
			}
		}
		// DelV를 실행할 시 6번 변수(5번 인덱스)에 관한 정보는 항상 초기화된다.
		// 6번 변수(5번 인덱스)을 삭제할 때도 동일
		Vlist[1][5] = 0;
		Vlist[2][5] = -1;
		VlistName[5] = "";
		Classcnt[5] = new int[] { 0, 0, 0 };
		// 모든 변수에 관한 정보 초기화
	}

	// 변수를 추가하기 위한 method
	public static void AddV(int newVWeight, String newVName, int Val, int Cnt[], int[][] Vlist, String[] VlistName,
			int[][] Classcnt) {
		int num = 0;

		// 추가하고자 하는 변수의 index(위치) 확인
		for (int i = 0; i <= 5; i++) {
			if (Vlist[1][i] > newVWeight) { // 가중치에 대한 배열은 내림차순으로 정렬되어있으므로
				// 가중치 비교를 통해 적절한 위치를 찾아준다.
				num += 1;
			}
		}

		// 공간을 확보하기 위헤
		// 찾은 인덱스 이상의 인덱스를 가진 변수들을 한 칸 씩 뒤로 밀어준다.
		for (int i = 5; i > num; --i) { // 거꾸로 설정을 하여, 모든 i값에 대해 예외사항이 없도록
			for (int j = 1; j <= 2; j++) {
				Vlist[j][i] = Vlist[j][i - 1];
			}
			VlistName[i] = VlistName[i - 1];
			Classcnt[i] = Classcnt[i - 1];
		}
		Vlist[1][num] = newVWeight;
		Vlist[2][num] = Val;
		VlistName[num] = newVName;
		Classcnt[num] = Cnt;
	}// 이미 6 변수가 존재하는 상황이면, 6변 변수를 밀어내고 새로운 변수가 자리잡는다.

	// 변수의 이름 정보 변경을 위한 method
	public static void Name(int modi, String modiName, String[] VlistName) {
		VlistName[modi - 1] = modiName;
	}

	// 변수의 변수값 정보 변경을 위한 method
	public static void Value(int modi, int modiVal, int[][] Vlist) {
		Vlist[2][modi - 1] = modiVal;
	}

	// 변수의 연관성 수치 정보 변경을 위한 method
	public static void Connect(int modi, int[] Cnt2, int[][] Classcnt) {
		Classcnt[modi - 1] = Cnt2;
	}

	// 전체 변수에 관한 간단한 정보를 제공
	public static void PrintVLess(String[] VlistName) {
		for (int i = 0; i <= 5; i++) {
			if (VlistName[i].equals("") == false) {
				System.out.printf((i + 1) + "번 변수:[%s]\t", VlistName[i]);
			}
		}
	}

	// 전체 변수에 대한 상세한 정보를 제공
	public static void PrintV(int[][] Vlist, String[] VlistName, int[][] Classcnt) {
		for (int i = 0; i <= 5; i++) {
			if (Vlist[2][i] != -1) {
				System.out.printf((i + 1) + "번 변수:[변수명: %s], [가중치: %d], [변수값: %d], [연관 수치: %s]\n", VlistName[i],
						Vlist[1][i], Vlist[2][i], Arrays.toString(Classcnt[i]));
			}
		}
	}

	// 특정한 한 변수에 대한 상세한 정보를 제공
	public static void PrintVMore(int modi, int[][] Vlist, String[] VlistName, int[][] Classcnt) {
		int i = modi - 1;
		System.out.printf(modi + "번 변수:[변수명: %s], [가중치: %d], [변수값: %d], [연관 수치: %s]\n", VlistName[i], Vlist[1][i],
				Vlist[2][i], Arrays.toString(Classcnt[i]));
	}

	// 최종 상황별 가능성 수치_계산 후 비교, 가장 전체 학생의 매점 방문 가능성이 적은 교시 리턴
	public static int Calcul(int[] timeList, int[][] Vlist, int[][] Classcnt) {
		for (int j = 0; j <= 2; j++) {
			int total = 0; // 상황(교시별) 가능성을 담는 변수
			for (int i = 0; i <= 5; i++) {
				// 가능성 수치 = 한 시간 대와 모든 변수에 대해(변수가중치*변수값*변수연관성)의 총합
				total += Vlist[1][i] * Vlist[2][i] * Classcnt[i][j];
			}
			// timeList에 들어있는 값이 0이어서 불가능한 시간대가 아니라면,
			// timeList의 해당 교시 인덱스에 total을 대입
			if (timeList[2 * j] != 0)
				timeList[2 * j] = total;
			if (timeList[2 * j + 1] != 0)
				timeList[2 * j + 1] = total;
		}
		// 7교시의 가능성은 항상 5,6 교시와 같다고 설정해두었으므로,
		timeList[6] = timeList[5];

		// 가능성이 최소인 경우를 찾기 위해
		// 비교할 배열 생성 - timeList의 값을 전부 복사
		int[] orgtList = new int[7];
		System.arraycopy(timeList, 0, orgtList, 0, timeList.length);

		// 복사해 두었으므로, 가장 작은 값을 알기 위해 정렬
		Arrays.sort(timeList);

		// 오름차순 정렬이므로, timeList의 마지막 인덱스의 값은 항상 최댓값.
		// 초기값으로 이를 둔다.
		int min = timeList[6];

		for (int i = 0; i <= 6; i++) {
			if (timeList[i] != 0) {// 0이 아닌 경우에(가능한 시간대에)
				if (timeList[i] < min) {
					min = timeList[i];
					break; // 정렬 후, if조건을 만족하는 첫번째 i에서 최솟값일 것이므로, 바로 break
				}
			}
		}
		// 최솟값을 가지는 교시를 구하기 위해 변수 minindex설정
		int minindex = 0;

		for (int i = 0; i <= 6; i++) {
			if (min == orgtList[i]) {// 복사해 둔 orgtList와 min의 값을 비교
				minindex = i;
				break;
			}
		}
		// minindex = 교시-1
		return (minindex + 1);
	}
}