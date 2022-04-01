(defproject com.github.yjcyxky/biodata-validator "0.0.0"
  :description "FIXME: write description"
  :url "https://github.com/yjcyxky/biodata-validator"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[commons-validator "1.7"]
                 [org.clojure/data.json "2.4.0"]]
  :resource-paths ["resources"]
  :plugins [[lein-cloverage "1.0.13"]
            [lein-shell "0.5.0"]
            [lein-ancient "0.6.15"]
            [lein-changelog "0.3.2"]
            [lein-typed "0.4.6"]]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.10.0"]]}}
  :deploy-repositories [["releases" :clojars]]
  :aliases {"update-readme-version" ["shell" "sed" "-i" "s/\\\\[com\\.github\\.yjcyxky\\\\/biodata-validator \"[0-9.]*\"\\\\]/[com\\.github\\.yjcyxky\\\\/biodata-validator \"${:version}\"]/" "README.md"]}
  :release-tasks [["shell" "git" "diff" "--exit-code"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["changelog" "release"]
                  ["update-readme-version"]
                  ["vcs" "commit"]
                  ["vcs" "tag"]
                  ["deploy"]
                  ["vcs" "push"]])
