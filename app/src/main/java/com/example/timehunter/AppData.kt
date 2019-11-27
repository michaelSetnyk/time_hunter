package com.example.timehunter

object ContactsData{
    val one = User("Person A")
    val two = User("Person B")
    val three =User("Person C")
    var contacts = arrayListOf(one,two,three)
}
// In an actual app this would be a database.
// But we need some dummy data and a DB is too much work

object GroupsData{
    private val hci_contacts= arrayListOf(ContactsData.one,ContactsData.three)
    private  val uoit_contacts= ContactsData.contacts
    private val hci_event = arrayListOf(Group("HCI Study Date", "Vote on the HCI study date", icon = R.drawable.hci ))

    private val hciGroup = Group("HCI","group to study HCI",R.drawable.hci, hci_contacts, events = hci_event)
    private val uoitGroup = Group("UOIT","Group from UOIT",R.drawable.uoit, uoit_contacts)


    var groups = arrayListOf(hciGroup, uoitGroup)
}

object CarouselData{
    val beerEvent = GroupEventItem("Beer Night", "This Friday Night 9pm", R.drawable.beer)
    val raptorEvent = GroupEventItem("Raptors Game", "This Saturday 7:30 pm", R.drawable.raptors)
    val hciGroup = GroupEventItem("HCI Study Group", "December 3rd 12:00pm", R.drawable.hci)

    var widgets = arrayListOf(beerEvent, raptorEvent, hciGroup)
}
 object NotificationsData{
     val beerNotification = Notification("Confirm Pickup", R.drawable.beer)
     val raptorNotification = Notification("Vote on HCI Study Day", R.drawable.hci)
     val HCINotification = Notification("All Riders Confirmed", R.drawable.raptors)

     var notifications = arrayListOf(beerNotification, raptorNotification, HCINotification)
 }


