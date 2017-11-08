export default function reducer(state = {
  items: [],
  fetching: false,
  fetched: false,
  error: null,
}, action) {

  switch (action.type) {
    case "GET_ITEMS": {
      return { ...state, fetching: true, fetched: false }
    }
    case "GET_ITEMS_REJECTED": {
      return { ...state, fetching: false, error: action.payload }
    }
    case "GET_ITEMS_FULFILLED": {
      return {
        ...state,
        fetching: false,
        fetched: true,
        items: action.payload,
      }
    }
    case "ADD_ITEM": {
      return { ...state };
    }
    case "DELETE_ITEM": {
      return { ...state };
    }
    case "UPDATE_ITEM": {
      return { ...state };
    }
    // case "UPDATE_ITEM": {
    //   const { id, text } = action.payload
    //   const newTweets = [...state.tweets]
    //   const tweetToUpdate = newTweets.findIndex(tweet => tweet.id === id)
    //   newTweets[tweetToUpdate] = action.payload;

    //   return {
    //     ...state,
    //     tweets: newTweets,
    //   }
    // }
    // case "DELETE_ITEM": {
    //   return {
    //     ...state,
    //     tweets: state.tweets.filter(tweet => tweet.id !== action.payload),
    //   }
    // }
  }


  return state
}
