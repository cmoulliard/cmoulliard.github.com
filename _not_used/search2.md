---
layout: page
title: "Posts Search engine"
description: "Posts Search engine."
---

<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.0.7/angular.min.js"></script>

<div ng-app="searchApp" ng-controller="PostListCtrl">

    <script>
    var searchApp = angular.module('searchApp',[]);

    searchApp.controller('PostListCtrl',['$scope', '$http', PostListCtrl]);
    searchApp.filter('matchesQuery', matchesQuery);

    function matchesQuery() {
        return function(items, query){

            var alternate = query.replace(/ /g,"_").toLowerCase();
            var lcQuery = query.toLowerCase();
            var arrayToReturn = [];

            for (var i=0; i<items.length; i++){
                if (items[i].title.toLowerCase().indexOf(lcQuery) !== -1
                               || items[i].words.indexOf(alternate) !== -1) {
                    console.log("result retrieved : " + items[i].title);
                    arrayToReturn.push(items[i]);
                }
            }
            return arrayToReturn;
        };
    };

    function PostListCtrl($scope, $http) {

       $scope.query = "";
       $scope.posts = [];

       $http.get('/my_posts.json').
       success(function(data) {
            $scope.posts = data.posts;
          });
    };

    </script>

    <p>Subset to posts containing these words: <input ng-model="query"></p>
    <p>Posts matching above query:</p>
    <ul>
        <li ng-repeat="post in posts | matchesQuery:query">
          {% raw %}
          <a ng-href="{{post.href}}">{{ post.title }}</a>
          {% endraw %}
        </li>
    </ul>
</div>