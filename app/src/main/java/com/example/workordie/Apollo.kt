package com.example.graphql

import com.apollographql.apollo3.ApolloClient

val apolloClient = ApolloClient.Builder()
    .serverUrl("https://ssgroup4.herokuapp.com/graphql")
    .build()