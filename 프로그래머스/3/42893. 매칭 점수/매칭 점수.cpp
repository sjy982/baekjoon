#include <bits/stdc++.h>
using namespace std;

static inline bool isalpha_lc(char c) {
    return c >= 'a' && c <= 'z';
}

// 소문자 문자열 s에서, 알파벳 연속열 단위로 word와 정확히 일치하는 개수 세기
int count_basic_score(const string& s_lower, const string& word) {
    int n = (int)s_lower.size(), m = (int)word.size();
    int cnt = 0;
    for (int i = 0; i < n; ) {
        if (isalpha_lc(s_lower[i])) {
            int j = i;
            while (j < n && isalpha_lc(s_lower[j])) j++;
            // [i, j) 가 하나의 단어
            if (j - i == m && s_lower.compare(i, m, word) == 0) cnt++;
            i = j;
        } else i++;
    }
    return cnt;
}

// s_lower 내에서 key를 찾아 시작지점 이후 첫 번째 따옴표쌍 "..." 안의 내용을 s 원본에서 추출
// (key는 소문자로 찾는다)
bool extract_quoted_after_key(const string& s, const string& s_lower,
                              const string& key, string& out) {
    size_t p = s_lower.find(key);
    if (p == string::npos) return false;
    // key 이후 첫 content=" 를 찾는다 (대소문자 무시 위해 s_lower에서 검색)
    size_t q = s_lower.find("content=\"", p);
    if (q == string::npos) return false;
    q += string("content=\"").size();
    size_t r = s_lower.find('"', q);
    if (r == string::npos) return false;
    out = s.substr(q, r - q);
    return true;
}

// s_lower에서 <a href="..."> 들을 모두 찾아 원본 s에서 URL을 추출
vector<string> extract_links(const string& s, const string& s_lower) {
    vector<string> links;
    const string pat = "<a href=\"";
    size_t pos = 0;
    while (true) {
        pos = s_lower.find(pat, pos);
        if (pos == string::npos) break;
        size_t start = pos + pat.size();
        size_t endq = s_lower.find('"', start);
        if (endq == string::npos) break;
        links.push_back(s.substr(start, endq - start));
        pos = endq + 1;
    }
    return links;
}

int solution(string word, vector<string> pages) {
    // 소문자 버전 준비
    auto to_lower = [](string t){
        for (char& c : t) c = (char)tolower((unsigned char)c);
        return t;
    };
    string word_lower = to_lower(word);

    const int N = (int)pages.size();
    vector<string> url(N);
    vector<int> basic(N, 0), outdeg(N, 0);
    vector<vector<string>> outlinks(N);
    vector<double> linkScore(N, 0.0);

    // 1) 각 페이지 파싱
    for (int i = 0; i < N; ++i) {
        const string& s = pages[i];
        string s_lower = to_lower(s);

        // (1) 자기 URL
        // <meta property="og:url" ... content="...">
        string myurl;
        bool ok = extract_quoted_after_key(
            s, s_lower, "<meta property=\"og:url\"", myurl
        );
        // 문제 조건상 항상 존재한다고 가정
        url[i] = myurl;

        // (2) 외부 링크들
        outlinks[i] = extract_links(s, s_lower);
        outdeg[i]   = (int)outlinks[i].size();

        // (3) 기본점수
        basic[i] = count_basic_score(s_lower, word_lower);
    }

    // 2) URL -> index 매핑
    unordered_map<string,int> idx;
    idx.reserve(N * 2);
    for (int i = 0; i < N; ++i) idx[url[i]] = i;

    // 3) 링크점수 전파
    for (int i = 0; i < N; ++i) {
        if (outdeg[i] == 0 || basic[i] == 0) continue;
        double share = (double)basic[i] / (double)outdeg[i];
        for (const string& to : outlinks[i]) {
            auto it = idx.find(to);
            if (it != idx.end()) {
                linkScore[it->second] += share;
            }
        }
    }

    // 4) 매칭점수 = 기본 + 링크, 최댓값 index (동점 시 작은 index)
    int answer = 0;
    double best = basic[0] + linkScore[0];
    for (int i = 1; i < N; ++i) {
        double cur = basic[i] + linkScore[i];
        if (cur > best) {
            best = cur; answer = i;
        }
    }
    return answer;
}
