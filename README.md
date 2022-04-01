# biodata-validator
[![Build Status](https://travis-ci.org/yjcyxky/biodata-validator.svg?branch=master)](https://travis-ci.org/yjcyxky/biodata-validator)
[![codecov](https://codecov.io/gh/yjcyxky/biodata-validator/branch/master/graph/badge.svg)](https://codecov.io/gh/yjcyxky/biodata-validator)
[![Clojars Project](https://img.shields.io/clojars/v/com.github.yjcyxky/biodata-validator.svg)](https://clojars.org/com.github.yjcyxky/biodata-validator)

A set of specs for biomedical metadata.

```clj
[com.github.yjcyxky/biodata-validator "0.1.0"]
```

## Usage

```clojure
(require '[biodata-validator.specs :as specs])
(require '[clojure.specs.alpha :as s])

(s/valid? :biodata-validator/age 50)
(s/valid? :biodata-validator/gender "F")
```

## License

Copyright © 2022 Jingcheng Yang

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
