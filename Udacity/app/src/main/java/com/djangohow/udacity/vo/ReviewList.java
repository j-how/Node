package com.djangohow.udacity.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by django on 2016/9/19.
 */
public class ReviewList {

    /**
     * id : 293660
     * page : 1
     * results : [{"id":"56c146cac3a36817f900d5f0","author":"huy.duc.eastagile","content":"A funny movie with a romantic love story. Wade Wilson (Ryan Reynolds) is a former Special Forces operative who now works as a mercenary. His world comes crashing down when evil scientist Ajax (Ed Skrein) tortures, disfigures and transforms him into Deadpool. The rogue experiment leaves Deadpool with accelerated healing powers and a twisted sense of humor. With help from mutant allies Colossus and Negasonic Teenage Warhead (Brianna Hildebrand), Deadpool uses his new skills to hunt down the man who nearly destroyed his life.","url":"https://www.themoviedb.org/review/56c146cac3a36817f900d5f0"},{"id":"56ca035a9251414a7a0062f0","author":"Wong","content":"I actually enjoyed the movie so much that i'll recommend it to all my friends, at first i didn't really want to watch it because i'm not into super hero movies at all, but i did anyway, i mean people were talking so much about it i had to see it myself and what an awesome choice i made. The good thing about this movie is that Deadpool is a hero but in a very comedic way, you don't usually expect comedy from a superhero film but this one was full of comedy and the way they treated the plot was amazing, it was there, humor was there in every scene, even when there was fighting or romance or any other scene, the writers managed to add comedy everywhere in a very good way that'll surprisingly make you want to watch it again, and again. Thank you for taking the time read my review and if you're asking yourself if you should watch this movie, it's a definite Yes.","url":"https://www.themoviedb.org/review/56ca035a9251414a7a0062f0"},{"id":"57375b8bc3a3687e1a0046bf","author":"Reno","content":"> So this guy in the red suit came and broke some records... I was not a Deadpool fan and I'm still not, but like any superhero films I'd pleasure watching it. The other reason was, we had other superheroes in our time, so this guy is kind of new to me. It was a stunning launch to the big screen for the Deadpool as well as for this new director. This film was merely an introduction tale than a fully stretched story. Like how a human becomes a mutant and later who seeks a revenge for ruining his natural life. But it yet to explain a couple of things and I hope it all will be revealed in its sequel. I am happy for Ryan Reynolds to get another chance, because his previous comic book character did not received well. I certainly liked the film, but nowadays there are plenty of superheroes to choose, in that, Deadpool's not in my top 5. So I am happy he joined the 'X-Men', because I never liked the boring 'X-Men' films or its characters, but I do love its spinoff 'Wolverine'. I'll definitely look forward for his solo films like 'Wolverine'. This film broke a few records like the best box office so far for an 'X-Men' installment. A short film with the present and flashback narration shared the screen piece by piece alternatively. This was a strong R rated film so far I have seen in a superhero theme and there were plenty of other films referred. No offense to the people who liked it (and those who are going watch will do the same), but in my personal opinion it was a bit over-celebrated. 6½/10","url":"https://www.themoviedb.org/review/57375b8bc3a3687e1a0046bf"},{"id":"57ad1b93925141588400129e","author":"talisencrw","content":"Though I have always been a DC enthusiast, I was intrigued by the idea of the Deadpool movie, and knew I'd end up watching it (I probably see 70% of DC films that are released and maybe 20% of Marvel ones). I enjoyed its definitely postmodern approach--and wish Reynolds' Green Lantern movie had one-tenth the humour and charm that THIS film had. It'll be very interesting to see how comic-book movies are affected, both short- and long-term by its massive success.","url":"https://www.themoviedb.org/review/57ad1b93925141588400129e"}]
     * total_pages : 1
     * total_results : 4
     */

    private int id;
    private int page;
    private int total_pages;
    private int total_results;
    /**
     * id : 56c146cac3a36817f900d5f0
     * author : huy.duc.eastagile
     * content : A funny movie with a romantic love story. Wade Wilson (Ryan Reynolds) is a former Special Forces operative who now works as a mercenary. His world comes crashing down when evil scientist Ajax (Ed Skrein) tortures, disfigures and transforms him into Deadpool. The rogue experiment leaves Deadpool with accelerated healing powers and a twisted sense of humor. With help from mutant allies Colossus and Negasonic Teenage Warhead (Brianna Hildebrand), Deadpool uses his new skills to hunt down the man who nearly destroyed his life.
     * url : https://www.themoviedb.org/review/56c146cac3a36817f900d5f0
     */

    private List<ResultsBean> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements Parcelable{
        private String id;
        private String author;
        private String content;
        private String url;

        protected ResultsBean(Parcel in) {
            id = in.readString();
            author = in.readString();
            content = in.readString();
            url = in.readString();
        }

        public static final Creator<ResultsBean> CREATOR = new Creator<ResultsBean>() {
            @Override
            public ResultsBean createFromParcel(Parcel in) {
                return new ResultsBean(in);
            }

            @Override
            public ResultsBean[] newArray(int size) {
                return new ResultsBean[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(author);
            dest.writeString(content);
            dest.writeString(url);
        }
    }
}
